/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;
import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maitreyi
 */
public class PostDao {
    Connection con;
    
    public PostDao(Connection con){
        this.con=con;
    }
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> list=new ArrayList<>();
        
        try{
            String q="Select * from categories";
            Statement st=con.createStatement();
            ResultSet set=st.executeQuery(q);
            while(set.next()){
                int cid=set.getInt("cid");
                String name=set.getString("name");
                String description=set.getString("description");
                Category c=new Category(cid,name,description);
                list.add(c);
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        return list;
        
    }
    public boolean savePost(Post p){
        boolean f=false;
        try{
            String q="insert into posts(pTitle,pContent,pCode,pPic,cId,userId) values(?,?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(q);
            pstmt.setString(1,p.getpTitle());
            pstmt.setString(2,p.getpContent());
            pstmt.setString(3,p.getpCode());
            pstmt.setString(4,p.getpPic());
            pstmt.setInt(5,p.getcId());
            pstmt.setInt(6,p.getUserId());
            pstmt.executeUpdate();
            f=true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        return f;
    }
    
    //get all the posts
    public List<Post> getAllPosts(){
        List<Post> list=new ArrayList<>();
        //fetch all the post
        try{
            PreparedStatement p=con.prepareStatement("Select * from posts order by pId desc");
            ResultSet set=p.executeQuery();
            while(set.next()){
               
                int pid = set.getInt("pId");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date=set.getTimestamp("pDate");
                int cId = set.getInt("cId");
                int userId = set.getInt("userId");
                

                Post post=new Post(pid,pTitle,pContent,pCode,pPic,null,cId,userId);
                list.add(post);
                
                
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        return list;
    }
    
    public List<Post> getPostByCatId(int cId){
         List<Post> list=new ArrayList<>();
        //fetch all the post by category Id
         try{
            PreparedStatement p=con.prepareStatement("Select * from posts where cId=?");
            p.setInt(1,cId);
            ResultSet set=p.executeQuery();
            while(set.next()){
               
                int pid = set.getInt("pId");
                String pTitle = set.getString("pTitle");
                String pContent = set.getString("pContent");
                String pCode = set.getString("pCode");
                String pPic = set.getString("pPic");
                Timestamp date=set.getTimestamp("pDate");
               
                int userId = set.getInt("userId");
                

                Post post=new Post(pid,pTitle,pContent,pCode,pPic,null,cId,userId);
                list.add(post);
                
                
                
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        return list;
        
    }
    
   
    
    
    
    
    
    
    
    
}
