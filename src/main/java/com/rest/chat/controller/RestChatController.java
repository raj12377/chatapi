package com.rest.chat.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.chat.domain.*;
import com.rest.chat.entity.Groupsmembers;
import com.rest.chat.entity.MessageEntity;
import com.rest.chat.entity.UserEntity;


import com.rest.chat.util.RestChatConstants;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@RestController
@RequestMapping(RestChatConstants.REST_CHAT_SERVER)
public class RestChatController {
	@Autowired 
	private HttpServletRequest request;
        private static SessionFactory factory; 
	@SuppressWarnings("static-access")
	@RequestMapping(value = RestChatConstants.USER_SIGNUP, method = RequestMethod.POST,produces="application/json")
	public @ResponseBody UserDocument userSignup(@RequestBody UserDocument usrDoc) {
		if (usrDoc != null) {
			String currentDate = RestChatConstants.dateFormat.format(new Date());
			usrDoc.setCreatedDate(currentDate);
			try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }	
			
      Session session = factory.openSession();
      Transaction tx = null;
      
      try{
                        tx = session.beginTransaction();
                        UserEntity user = new UserEntity();
			user.setUserId(usrDoc.getUserId());
			user.setUserName(usrDoc.getUserName());
			user.setCreatedDate(usrDoc.getCreatedDate());
			user.setEmail(usrDoc.getEmail());
			session.save(user);
                        tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
		}
		 return  usrDoc;
	}
        @SuppressWarnings("static-access")
	@RequestMapping(value = RestChatConstants.USER_LIST, method = RequestMethod.GET,produces="application/json")
        public @ResponseBody String listUsers( ){
            String response="";
            	try{
                     factory = new Configuration().configure().buildSessionFactory();
                    }catch (Throwable ex) { 
                                 System.err.println("Failed to create sessionFactory object." + ex);
                                    throw new ExceptionInInitializerError(ex); 
                     }	
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List users = session.createQuery("FROM UserEntity").list(); 
         for (Iterator iterator = 
                           users.iterator(); iterator.hasNext();){
            UserEntity uent = (UserEntity) iterator.next(); 
            response+=("user Id: " + uent.getUserId()+"  user Name: " + uent.getUserName()+"\n"); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
            
      return response;
   }
        @SuppressWarnings("static-access")
	@RequestMapping(value = RestChatConstants.MESSAGE_SEARCH, method = RequestMethod.POST,produces="application/json")
        public @ResponseBody String listmessages(@RequestBody String gid ){
            String response="";
            	try{
                     factory = new Configuration().configure().buildSessionFactory();
                    }catch (Throwable ex) { 
                                 System.err.println("Failed to create sessionFactory object." + ex);
                                    throw new ExceptionInInitializerError(ex); 
                     }	
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List<MessageEntity> messages =(List<MessageEntity>) session.createQuery("FROM MessageEntity where guId="+gid).list(); //"'"+
         for (Iterator iterator = 
                           messages.iterator(); iterator.hasNext();){
           MessageEntity m= (MessageEntity) iterator.next(); 
            response+=(m.getmessage()+"\n"); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return response;
        }
	@SuppressWarnings("static-access")
	@RequestMapping(value = RestChatConstants.USER_SEARCH, method = RequestMethod.POST,produces="application/json")
        public @ResponseBody String searchUsers(@RequestBody String name ){
            String response="";
            	try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }	
      Session session = factory.openSession();
      Transaction tx = null;
      try{
        tx = session.beginTransaction();
        
         List users = session.createQuery("FROM UserEntity where userName="+"'"+name+"'").list(); 
         for (Iterator iterator = 
                           users.iterator(); iterator.hasNext();){
            UserEntity uent = (UserEntity) iterator.next(); 
            response+=("user Id: " + uent.getUserId()+"  user Name: " + uent.getUserName()+"\n"); 
         }
         if(response=="")
             response ="no person with that name";
         
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
            
      return response;
   }
	@SuppressWarnings("static-access")
	@RequestMapping(value = RestChatConstants.CHAT_CREATE, method = RequestMethod.POST)
	public @ResponseBody String createGroup( @RequestBody GroupDocument grpDoc) {
		String response = " ";
                if (grpDoc != null) {
			try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }	
			
      Session session = factory.openSession();
      Transaction tx = null;
      
      try{
                        tx = session.beginTransaction();
                        Groupsmembers grpmem = new Groupsmembers();
			grpmem.setGuId(grpDoc.getGuId());
			grpmem.setGroupName(grpDoc.getGroupName());
			grpmem.setMembers(String.valueOf(grpDoc.getUserId1())+","+String.valueOf(grpDoc.getUserId2()));
			
			session.save(grpmem);
                        tx.commit();
                        response="added group succesfully";
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         response="group addition not successfull";
         e.printStackTrace(); 
         
      }finally {
         session.close(); 
      }
		}
                return response;
		
	}
	
	
	
	@RequestMapping(value = RestChatConstants.CHAT_POST, method = RequestMethod.POST)
	public @ResponseBody String postmessage(@RequestBody MessageDocument1 msg) {
		String response = "";
                MessageEntity msgent=new MessageEntity();
               if (msg != null) {
			String currentDate = RestChatConstants.dateFormat.format(new Date());
			msgent.setstampTime(currentDate);
			try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }	
			
      Session session = factory.openSession();
      Transaction tx = null;
      
      try{
                        tx = session.beginTransaction();
                     
			msgent.setGroupName(msg.getGroupName());
                        msgent.setGuId(msg.getGuId());
                        msgent.setUserId(msg.getId());
                        msgent.setsessionid(msg.getsessionid());
                        msgent.setmessage(msg.getMessageContent());
			
			session.save(msgent);
                        tx.commit();
                        response="Message posted successfully";
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
          response="Message not posted";
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
		}
		return response;
	}
	
}
