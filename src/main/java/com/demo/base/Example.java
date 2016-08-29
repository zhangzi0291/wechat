package com.demo.base;

/**
 * 公用查询条件扩展类，增加分页信息
 * @author liulang
 * @version Current version:&nbsp;&nbsp;V1.0<br>
 * <hr style="width:80%; float:left;">
 * <table style="width:100%;">
 * <tr><td width="20%" align="left">Operator</td><td width="30%" align="left">Time</td><td width="50%" align="left">Describe</td></tr>
 * <tr><td>liulang</td><td>2014年6月15日 下午2:54:33</td><td>create&nbsp;V1.0</td></tr>
 * </table>
 * @since   JDK 1.6
 */
public interface Example {

    /**
     * 设置分页信息
     * @param page
     */
    public void setPage(Page page);

    /**
     * 获取分页信息
     * @return
     */
    public Page getPage();
   
}
