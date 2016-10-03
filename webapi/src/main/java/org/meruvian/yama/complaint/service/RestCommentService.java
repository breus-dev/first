package org.meruvian.yama.complaint.service;

import javax.inject.Inject;
import org.meruvian.yama.complaint.entity.Comment;
import org.meruvian.yama.complaint.repository.CommentRepository;
import org.meruvian.yama.core.LogInformation;
import org.meruvian.yama.web.SessionCredentials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestCommentService implements CommentService {

	@Inject
	private CommentRepository commentRepository;

	public CommentRepository getCommentRepository() {
		return commentRepository;
	}

	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public Comment getCommentById(String id) {
		// TODO Auto-generated method stub
		return commentRepository.findById(id);
	}

	@Override
	public Page<Comment> findCommentByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return commentRepository.findByTitleOrderByCreateDateDesc(keyword, LogInformation.ACTIVE, pageable);
	}

	@Override
	public void removeComment(String id) {
		// TODO Auto-generated method stub
		getCommentById(id).getLogInformation().setActiveFlag(LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Comment saveComment(Comment comment) {
		// TODO Auto-generated method stub
			comment.setId(null);
			comment.setTitle(comment.getTitle());
			comment.setComment(comment.getComment());
			comment.setComplaint(comment.getComplaint());
			comment.setAuthor(SessionCredentials.getCurrentUser());
			
			return commentRepository.save(comment);
	
	}

	@Override
	@Transactional
	public Comment updateComment(String id, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getComplaintById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Comment> getCommentByComplaintId(String id, Pageable pageable) {
		return commentRepository.getCommentByComplaintId(id, LogInformation.ACTIVE, pageable);
	}

}