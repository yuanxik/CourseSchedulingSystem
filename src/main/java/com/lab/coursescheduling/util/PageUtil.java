package com.lab.coursescheduling.util;/*
    @auther
    @create ---
*/

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public class PageUtil {
    /**
     * 将PageInfo对象泛型中的Po对象转化为Vo对象
     * @param pageInfoPo PageInfo<Po>对象</>
     * @param <P> Po类型
     * @param <V> Vo类型
     * @return
     */
    public static <P, V> PageInfo<V> PageInfo2PageInfoVo(PageInfo<P> pageInfoPo) {
        // 创建Page对象，实际上是一个ArrayList类型的集合
        Page<V> page = new Page<>(pageInfoPo.getPageNum(), pageInfoPo.getPageSize());
        page.setTotal(pageInfoPo.getTotal());
        return new PageInfo<>(page);
    }

}
