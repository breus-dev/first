package org.meruvian.yama.complaint.service;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.meruvian.yama.complaint.entity.Category;
import org.meruvian.yama.complaint.repository.CategoryRepository;
import org.meruvian.yama.core.LogInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestCategoryService implements CategoryService{
	
	@Inject
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(String id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public Page<Category> findCategoryByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return categoryRepository.findByNameOrDescription(keyword,
			keyword, LogInformation.ACTIVE, pageable);
	}

	@Override
	@Transactional
	public void removeCategory(String id) {
		// TODO Auto-generated method stub
		getCategoryById(id).getLogInformation().setActiveFlag(
			LogInformation.INACTIVE);
	}

	@Override
	@Transactional
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(category.getId())){
			category.setId(null);
			category.setName(category.getName());
			return categoryRepository.save(category);
		}
		return null;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		Category r = categoryRepository.findById(category.getId());
		r.setName(category.getName());
		r.setDescription(category.getDescription());
		return null;
	}

	
	
	
}
