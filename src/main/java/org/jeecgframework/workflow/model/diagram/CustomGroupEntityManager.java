/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.activiti.engine.identity.Group;
/*    */ import org.activiti.engine.impl.persistence.entity.GroupEntity;
/*    */ import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.RowCallbackHandler;
/*    */ 
/*    */ public class CustomGroupEntityManager extends GroupEntityManager
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private JdbcTemplate jdbcTemplate;
/*    */ 
/*    */   public List<Group> findGroupsByUser(String userId)
/*    */   {
/* 22 */     System.out.println("liujinghua...... findGroupsByUser  ");
/*    */ return super.findGroupsByUser(userId);
/* 24 */    // String sql = "select t_s_user_depart.groupid from t_s_base_user,t_s_user_depart where t_s_base_user.username=t_s_user_depart.username and t_s_base_user.username='" + userId + "'";
/*    */ 
/* 26 */    /* List<GroupEntity> groups = new ArrayList();
 27      this.jdbcTemplate.query(sql, new RowCallbackHandler(groups) {
           public void processRow(ResultSet rs) throws SQLException {
 29          this.val$groups.add(new GroupEntity(rs.getString("groupid")));
 30          System.out.println(rs.getString("groupid") + "-----------------------------------------");
           }
         });
 34      return groups;*/
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.CustomGroupEntityManager
 * JD-Core Version:    0.6.0
 */