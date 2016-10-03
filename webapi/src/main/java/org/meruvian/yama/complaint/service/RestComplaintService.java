package org.meruvian.yama.complaint.service;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.complaint.entity.Comment;
import org.meruvian.yama.complaint.entity.Complaint;
import org.meruvian.yama.complaint.entity.Complaint.Status;
import org.meruvian.yama.complaint.repository.CommentRepository;
import org.meruvian.yama.complaint.repository.ComplaintRepository;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestComplaintService implements ComplaintService {

	@Inject
	private ComplaintRepository complaintRepository;

	@Inject
	private CommentRepository commentRepository;
	
	@Override
	public Complaint getComplaintById(String id) {
		// TODO Auto-generated method stub
		return complaintRepository.findById(id);
	}

	@Override
	public Page<Complaint> findComplaintByKeyword(String keyword, Status status, Pageable pageable) {
		// TODO Auto-generated method stub
		if (status == null) {
			return complaintRepository.findByTitleOrMessageOrderByCreateDateDesc(keyword, keyword, LogInformation.ACTIVE, pageable);
		}
		
		return complaintRepository.findByTitleOrMessageAndStatusOrderByCreateDateDesc(keyword, keyword, status, LogInformation.ACTIVE, pageable);
	}

	@Override
	public Page<Comment> findCommentsByComplaintId(String id, Pageable pageable) {
		return commentRepository.findByComplaintId(id, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public void removeComplaint(String id) {
		// TODO Auto-generated method stub
		getComplaintById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Complaint saveComplaint(Complaint complaint) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(complaint.getId())){
			complaint.setId(null);
			complaint.setTitle(complaint.getTitle());
			complaint.setMessage(complaint.getMessage());
			complaint.setCategory(complaint.getCategory());
			complaint.setAuthor(SessionCredentials.getCurrentUser());
			// System.out.println("user id: "+SessionCredentials.getCurrentUser().getId()	);
		/*	complaint.setAuthor(complaint.getAuthor());*/
			complaint.setStatus(Status.PRIVATE);
			return complaintRepository.save(complaint);
		}
		throw new BadRequestException("you must put");
	}

	@Override
	@Transactional
	public Complaint updateComplaint(String id,Complaint complaint) {
		// TODO Auto-generated method stub
		Complaint r = complaintRepository.findById(complaint.getId());
		r.setTitle(complaint.getTitle());
		r.setMessage(complaint.getMessage());
		r.setCategory(complaint.getCategory());
		r.setStatus(complaint.getStatus());
		return r;
	}
	
	
}
