package service;

import mapper.TagMapper;
import model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
	@Autowired
	private TagMapper tagMapper;

	public List<Tag> getTagList() {
		List<Tag> tagList = tagMapper.getAllTagList();
		return tagList;
	}

	public List<Tag> getArticleTagList(String id) {
		return tagMapper.getArticleTagList(id);
	}

}
