package service;

import mapper.AdminMapper;
import model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminMapper adminMapper;

	public boolean validateManager(Manager manager) {

		return adminMapper.validateManager(manager) > 0 ? true : false;
	}

	public Manager getManagerInfo(Manager manager) {

		return adminMapper.getManagerInfo(manager);
	}

}
