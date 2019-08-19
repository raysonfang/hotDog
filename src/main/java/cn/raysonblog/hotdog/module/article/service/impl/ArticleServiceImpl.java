package cn.raysonblog.hotdog.module.article.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;


import cn.raysonblog.hotdog.module.article.mapper.ArticleMapper;
import cn.raysonblog.hotdog.module.article.entity.ArticleEntity;
import cn.raysonblog.hotdog.module.article.service.IArticleService;
import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, ArticleEntity> implements IArticleService {

}
