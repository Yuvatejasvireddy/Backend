package com.teju.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teju.config.JwtProvider;
import com.teju.model.Task;
import com.teju.model.User;
import com.teju.repository.TaskRepository;
import com.teju.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository ur;

	@Autowired 
	TaskService ts;
	
	@Autowired
	TaskRepository tr;
	public User getuserProfile(String jwt) {
		String username=JwtProvider.getEmailFromJwtToken(jwt);
		User u=ur.findbyEmail(username);
		return u;
	}
	
	public List<User> findAllUsersByUserRole(){
		return ur.findAllUsersByUserRole();
	}

	public String deletebyId(Long uid) {
		User u=ur.findById(uid).orElse(null);
		
		List<Task> tasks = ts.assignedusertask(uid,null);
        for (Task task : tasks) {
            // Set assigned user to null and update status
            task.setAssigneduserid(null);
            task.setStatus("Not Assigned");
            tr.save(task);
        }
		if(u==null)
			return "No";
		ur.deleteById(uid);
		
		return "Yes";
	};
	
}
