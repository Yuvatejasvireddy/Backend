package com.teju.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teju.model.Task;
import com.teju.repository.TaskRepository;

@Service
public class TaskService  {
	
	@Autowired
	TaskRepository tr;
	
	
	
	//creating a task 
	//only admin can create that 
	//default is pending 
	
	public Task createtask(Task task,String role) throws Exception
	{
		if(!role.equals("admin"))
		{
			throw new Exception("only admin can create a new task");
		}
		
		task.setStatus("NOT ASSIGNED");
		task.setCreatedat(LocalDateTime.now());
		return tr.save(task);
	}
	
	//getting task by id
	public Task getbyid(Long id) throws Exception
	{
		Task t=tr.findById(id).orElse(null);
		
		if(t==null)
			throw new Exception("Task with id "+id +" not found");
		return t;
		
	}
	
	//get all task filter with status 
	public List<Task> getallTask(String status)
	{
		List<Task> alltasks=tr.findAll();
		if(status==null || status.trim().isEmpty())
			return alltasks;
		
//		// handling null point exception
//		if (alltasks == null) {
//            throw new IllegalStateException("Failed to retrieve tasks for user: " + userId);
//        }
//		
		
		
		List<Task> filteredtasks=alltasks.stream().filter(task->status==null || task.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		
//		 List<Task> filteredtasks = alltasks.stream()
//		            .filter(task -> isStatusMatching(task, status))
//		            .collect(Collectors.toList());
		        
		return filteredtasks;
	}
	
	public List<Task> getallTaskdone(String done) {
	    List<Task> alltasks = tr.findAll();
	    
	    // If done is null or empty, return an empty list
	    if (done == null || done.trim().isEmpty()) {
	        return Collections.emptyList();
	    }
	    
	    // Filter tasks based on the status
	    List<Task> filteredtasks = alltasks.stream()
	                                       .filter(task -> task.getDone() != null && task.getDone().equalsIgnoreCase(done))
	                                       .collect(Collectors.toList());

	    return filteredtasks;
	}

	
//	private boolean isStatusMatching(Task task, String status) {
//       
//        if (task == null) {
//            throw new IllegalArgumentException("Task cannot be null");
//        }
//        
//        
//        if (Objects.isNull(status) || status.trim().isEmpty()) {
//            return true;
//        }
//        
//        
//        return task.getStatus().equalsIgnoreCase(status.trim());
//    }
	
	
	//updating the task(user editing the task)
	public Task updateTask(Long taskid,Task updatedtask,Long Userid) throws Exception
	{
		System.out.println(updatedtask.getStatus());
		Task t=tr.findById(taskid).orElse(null);
		if(t==null)
			throw new Exception("Task with id " + taskid+" dosen't exist");
		else
		{
//			if(t.getAssigneduserid()!=Userid)
//				throw new Exception("This task is not assigned to you");
			
			if(updatedtask.getImage()!=null)
				t.setImage(updatedtask.getImage());
			
			if(updatedtask.getTitle()!=null)
				t.setTitle(updatedtask.getTitle());
			
			if(updatedtask.getDescription()!=null)
				t.setDescription(updatedtask.getDescription());
			
			if(updatedtask.getTags()!=null)
				t.setTags(updatedtask.getTags());
			
			if(updatedtask.getDeadline()!=null)
				t.setDeadline(updatedtask.getDeadline());
			
			if(updatedtask.getStatus()!=null)
				t.setStatus(updatedtask.getStatus());
			if(updatedtask.getDone()!=null)
				t.setStatus(updatedtask.getDone());
			
			return tr.save(t);
		}
	}
	
	//delete task
	public void deletetask(Long taskid)
	{
		tr.deleteById(taskid);
		
	}
	
	
	//assigning user to a task
	public Task assignedtouser(Long taskid,Long userId) throws Exception
	{
		
	  Task t=getbyid(taskid);
	  t.setAssigneduserid(userId);
	  t.setStatus("ASSIGNED");
	  return tr.save(t);
	  
	  
	}
	
	//list of tasks assigned to a user with a status
	public List<Task> assignedusertask(Long userid,String status)
	{
        List<Task> alltasks=tr.findByAssignedUser(userid);
		
		List<Task> filteredtasks=alltasks.stream().filter(task->status==null || task.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
		return filteredtasks;
	}
	
	//complete task
	
	public Task completeTask(Long id) throws Exception
	{
		Task t=getbyid(id);
		t.setStatus("done");
		return tr.save(t);
		
	}
	
	
	
	
	
	
	
	
	
	

}
 