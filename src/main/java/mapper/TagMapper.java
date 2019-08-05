package mapper;

import model.Tag;

import java.util.List;

public interface TagMapper {

	List<Tag> getTagList();

	List<Tag> getArticleTagList(String id);

	List<Tag> getAllTagList();
	
}
