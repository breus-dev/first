	package org.meruvian.yama.complaint.repository;

import org.meruvian.yama.complaint.entity.Complaint;
import org.meruvian.yama.complaint.entity.Complaint.Status;
import org.meruvian.yama.core.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepository extends DefaultRepository<Complaint>{
	@Query("SELECT c FROM Complaint c WHERE (c.title LIKE %?1% OR c.message "
			+"LIKE %?2%) AND c.logInformation.activeFlag = ?3 ORDER BY c.logInformation.createDate DESC")
	Page<Complaint> findByTitleOrMessageOrderByCreateDateDesc(String Title, String Message, int activeFlag, Pageable pageable);
	
	@Query("SELECT c FROM Complaint c WHERE (c.title LIKE %?1% OR c.message "
			+"LIKE %?2%) AND c.status = ?3 AND c.logInformation.activeFlag = ?4 ORDER BY c.logInformation.createDate DESC")
	Page<Complaint> findByTitleOrMessageAndStatusOrderByCreateDateDesc(String Title, String Message, Status status, int activeFlag, Pageable pageable);	
	
	@Query("SELECT c FROM Complaint c WHERE c.title =?1 AND"
			+" c.logInformation.activeFlag = ?2 ")
	Complaint getByTitle(String Title, int activeFlag);
}
