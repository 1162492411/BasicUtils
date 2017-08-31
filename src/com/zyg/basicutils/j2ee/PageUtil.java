package com.zyg.basicutils.j2ee;

/**
 * 分页工具类
 * Created by Mo on 2017/7/27.
 */
public class PageUtil {
    private static int PAGE_SIZE = 10;//默认每页数量

    /**
     * 根据总数获取总页数
     * @param totalCount 总数
     * @return 总页数
     */
    public static int getTotalPage(int totalCount){
        if(totalCount == 0) return 0;
        return totalCount % PAGE_SIZE == 0 ? totalCount / PAGE_SIZE : totalCount / PAGE_SIZE + 1 ;
    }

    /**
     * 获取合理的当前分页数
     * @param page 当前分页数，一般从1开始
     * @param totalPage 总页数
     * @return 合理的当前分页数
     */
    public static int getCurrentPage(int page, int totalPage){
        if(totalPage == 0) return 0;
        return page < 1 ? 1 : page > totalPage? totalPage : page;
    }

    /**
     * 获取合理的查询起始位置
     * 该方法常用于dao层或service层
     * @param page 前台传入的查询的页数，一般从1开始
     * @return 合理的起始位置，该位置从2开始
     */
    public static int getStart(int page){
        return page <= 0 ? 0 : (page - 1) * PAGE_SIZE;
    }

}

