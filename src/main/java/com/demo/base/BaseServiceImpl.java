package com.demo.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公用的Service实现类，继承实现类的要传递2个泛型类型，第一个是实体Bean类型，第二个是查询Bean类型。
 * @author liulang
 * @version Current version:&nbsp;&nbsp;V1.0<br>
 * <hr style="width:80%; float:left;">
 * <table style="width:100%;">
 * <tr><td width="20%" align="left">Operator</td><td width="30%" align="left">Time</td><td width="50%" align="left">Describe</td></tr>
 * <tr><td>liulang</td><td>2014年6月15日 下午2:31:06</td><td>create&nbsp;V1.0</td></tr>
 * </table>
 * @since   JDK 1.6
 */
public abstract class BaseServiceImpl<T, E> implements BaseService<T, E> {
	private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	private BaseDao<T, E> _baseDao;
	
	/**
	 * 将继承该类的子类中的BaseDao赋值给该类中的_baseDao,通过调用子类的getDao()或者获取子类的dao<BaseDao>属性（该属性需要是public）
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setBaseDao()  throws Exception {
		try {
			_baseDao = getDao();
			if(logger.isDebugEnabled()) {
				logger.debug("从getDao()获取的_baseDao是{}", _baseDao);
			}
			if(null != _baseDao) return;
			Field[] fields = getClass().getDeclaredFields();
			for(Field f : fields) {
				f.setAccessible(true);
				Object obj = f.get(this);
				if(obj instanceof BaseDao) {
					_baseDao = (BaseDao<T, E>)obj;
					if("dao".equals(f.getName())) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public int countByExample(E example) throws DaoException {
		return _baseDao.countByExample(example);
	}

	public int deleteByExample(E example) throws DaoException {
		return _baseDao.deleteByExample(example);
	}

	public List<T> selectByExample(E example) throws DaoException {
		return _baseDao.selectByExample(example);
	}

	public int updateByExampleSelective(T record, E example) throws DaoException {
		return _baseDao.updateByExampleSelective(record, example);
	}

	public int updateByExample(T record, E example) throws DaoException {
		return _baseDao.updateByExample(record, example);
	}

	public int insert(T record) throws DaoException {
		return _baseDao.insert(record);
	}

	public int insertSelective(T record) throws DaoException {
		return _baseDao.insertSelective(record);
	}

	/**
	 * 分页查询
	 * @param example 查询条件
	 * @return 分页查询的结果
	 * @throws DaoException
	 * @see cn.howso.framework.base.service.BaseService#selectByExampleAndPage(java.lang.Object)
	 */
	public PageList<T> selectByExampleAndPage(E example) throws DaoException {
		if(null != example) {
			Page page = null;
			List<T> list = null;
			int count = 0;
			if(example instanceof Example) {
				page = ((Example) example).getPage();
				((Example) example).setPage(null);
				count = _baseDao.countByExample(example);
				
				if(count > 0) {
					((Example) example).setPage(page);
					list = _baseDao.selectByExample(example);
				}
			}
			if(null == list) list = new ArrayList<T>(0);
			PageList<T> pageList = new PageList<T>(list, count);
			return pageList;
		}
		return new PageList<T>(new ArrayList<T>(0), 0);
	}

	public int deleteByPrimaryKey(Serializable primaryKey) throws DaoException {
		return _baseDao.deleteByPrimaryKey(primaryKey);
	}

	public T selectByPrimaryKey(Serializable primaryKey) throws DaoException {
		return _baseDao.selectByPrimaryKey(primaryKey);
	}

	public int updateByPrimaryKeySelective(T record) throws DaoException {
		return _baseDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(T record) throws DaoException {
		return _baseDao.updateByPrimaryKey(record);
	}

	/**
	 * 默认实现返回null
	 * @return
	 * @throws DaoException
	 * @see cn.howso.framework.base.service.BaseService#getDao()
	 */
	public abstract BaseDao<T, E> getDao() throws DaoException;


}
