package org.meruvian.yama.complaint.repository;

import org.meruvian.yama.complaint.entity.Category;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends DefaultRepository<Category>{
	
	@Query("SELECT c FROM Category c WHERE (c.name LIKE %?1% OR c.description"
			+" LIKE %?2%) AND c.logInformation.activeFlag = ?3")
	Page<Category> findByNameOrDescription(String Name, String Description, int activeFlag, Pageable pageable);
	
	@Query("SELECT c FROM Category c WHERE c.name =?1 AND"
			+" c.logInformation.activeFlag = ?2 ")
	Category getByName(String Name, int activeFlag);
}
