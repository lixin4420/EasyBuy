package cn.easybuy.dao.news;

import cn.easybuy.entity.News;
import cn.easybuy.param.NewsParams;

import java.util.List;

public interface NewsMapper {
    /**
     * 统计一共有多少条资讯新闻
     */
    public int queryNewsCount(NewsParams params)throws Exception;
    /**
     * 查询资讯新闻列表
     */
    public List<News> queryNewsList(NewsParams params)throws Exception;

}
