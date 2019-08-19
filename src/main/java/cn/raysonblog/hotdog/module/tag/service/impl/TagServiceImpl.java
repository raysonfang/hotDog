package cn.raysonblog.hotdog.module.tag.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;


import cn.raysonblog.hotdog.module.tag.mapper.TagMapper;
import cn.raysonblog.hotdog.module.tag.entity.TagEntity;
import cn.raysonblog.hotdog.module.tag.service.ITagService;
import cn.raysonblog.hotdog.base.service.impl.BaseServiceImpl;

@Service
public class TagServiceImpl extends BaseServiceImpl<TagMapper, TagEntity> implements ITagService {

}
