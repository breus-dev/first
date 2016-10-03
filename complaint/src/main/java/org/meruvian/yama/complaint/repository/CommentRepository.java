package org.meruvian.yama.complaint.repository;

import org.meruvian.yama.complaint.entity.Comment;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends DefaultRepository<Comment>{
	
	@Query("SELECT c FROM Comment c WHERE (c.title LIKE %?1%) "
			+ "AND c.logInformation.activeFlag = ?2")
	Page<Comment> findByTitleOrderByCreateDateDesc(String keyword,  
			int active, Pageable pageable);
	
	@Query("SELECT c FROM Comment c WHERE c.title =?1 AND"
			+" c.logInformation.activeFlag = ?2 ")
	Comment getByTitle(String Title, int activeFlag);
	
	@Query("SELECT c FROM Comment c WHERE c.complaint.id = ?1 AND c.logInformation.activeFlag = ?2")
	Page<Comment> findByComplaintId(String id, int active, Pageable pageable);
	
	@Query("SELECT i FROM Comment i WHERE i.complaint.id = ?1 AND "
			+ "i.logInformation.activeFlag = ?2")
	Page<Comment> getCommentByComplaintId(String id, int activeFlag, Pageable pageable);
}