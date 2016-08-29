package com.demo.base;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 公用的DAO接口，继承或实现该接口要传递2个泛型类型，第一个是实体Bean类型，第二个是查询Bean类型。
 * @author liulang
 * @version Current version:&nbsp;&nbsp;V1.0<br>
 * <hr style="width:80%; float:left;">
 * <table style="width:100%;">
 * <tr><td width="20%" align="left">Operator</td><td width="30%" align="left">Time</td><td width="50%" align="left">Describe</td></tr>
 * <tr><td>liulang</td><td>2014年6月15日 下午2:12:24</td><td>create&nbsp;V1.0</td></tr>
 * </table>
 * @since   JDK 1.6
 */
public interface BaseDao<T, E> {
	
	/**
	 * 查询记录数
	 * @param example 查询条件
	 * @return 记录数
	 */
	int countByExample(E example);

    /**
     * 根据自定义条件删除记录
     * @param example 删除条件
     * @return 删除的记录数
     */
    int deleteByExample(E example);
    
    /**
     * 根据主键删除记录
     * @param primaryKey 主键
     * @return 删除的记录数
     */
    int deleteByPrimaryKey(Serializable primaryKey);

    /**
     * 根据主键查询
     * @param primaryKey 主键
     * @return 查询到的记录
     */
    T selectByPrimaryKey(Serializable primaryKey);
    
    /**
     * 根据自定义条件查询
     * @param example 查询条件
     * @return 查询到的记录
     */
    List<T> selectByExample(E example);

    /**
     * 根据条件更新数据，只更新record中非空的字段
     * @param record 实体Bean
     * @param example 查询条件
     * @return 更新的记录数
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);
    
    /**
     * 根据主键更新数据，只更新record中非空的字段
     * @param record 实体Bean
     * @return 更新的记录数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据条件更新数据，record中的空字段也会更新到数据库对应字段
     * @param record 实体Bean
     * @param example 查询条件
     * @return 更新的记录数
     */
    int updateByExample(@Param("record") T record, @Param("example") E example);
    
    /**
     * 根据主键更新数据，record中的空字段也会更新到数据库对应字段
     * @param record 实体Bean
     * @return 更新的记录数
     */
    int updateByPrimaryKey(T record);
    
//    List<T> selectByExampleAndPage(E example, RowBounds rowBound);

    /**
     * 插入一条记录，record中的空字段也会插入到数据库中
     * @param record 实体Bean
     * @return 插入的记录数
     */
    int insert(T record);

    /**
     * 插入一条记录，只插入record中非空的字段
     * @param record 实体Bean
     * @return 插入的记录数
     */
    int insertSelective(T record);
}
