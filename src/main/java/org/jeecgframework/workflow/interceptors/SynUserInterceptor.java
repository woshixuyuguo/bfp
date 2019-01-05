/*     */ package org.jeecgframework.workflow.interceptors;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.jeecgframework.core.constant.Globals;
/*     */ import org.jeecgframework.core.util.ResourceUtil;
/*     */ import org.jeecgframework.core.util.StringUtil;
/*     */ import org.jeecgframework.web.system.pojo.base.TSDepart;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ import org.jeecgframework.web.system.service.SystemService;
/*     */ import org.jeecgframework.workflow.common.WorkFlowGlobals;
/*     */ import org.jeecgframework.workflow.service.ActivitiService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.servlet.HandlerInterceptor;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ public class SynUserInterceptor
/*     */   implements HandlerInterceptor
/*     */ {
/*     */   private List<String> includeUrls;
/*  24 */   private static final Logger logger = Logger.getLogger(SynUserInterceptor.class);
/*     */ 
/*     */   @Autowired
/*     */   private ActivitiService activitiService;
/*     */ 
/*     */   @Autowired
/*     */   private SystemService systemService;
/*  29 */   private String message = null;
/*     */ 
/*     */   public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
/*     */     throws Exception
/*     */   {
/*  34 */     String requestPath = ResourceUtil.getRequestPath(arg0);
/*  35 */     if (!this.includeUrls.contains(requestPath)) {
/*  36 */       return;
/*     */     }
/*     */ 
/*  39 */     String userId = arg0.getParameter("id");
/*  40 */     String roleid = arg0.getParameter("roleid");
/*  41 */     String activitiSync = arg0.getParameter("activitiSync");
/*  42 */     if (requestPath.indexOf("userController.do?saveUser") != -1) {
/*  43 */       if (StringUtil.isEmpty(activitiSync)) {
/*  44 */         return;
/*     */       }
/*  46 */       if (StringUtil.isNotEmpty(roleid)) {
/*  47 */         if (StringUtil.isEmpty(userId))
/*     */         {
/*  49 */           TSUser user = new TSUser();
/*  50 */           user.setUserName(arg0.getParameter("userName"));
/*  51 */           user.setEmail(arg0.getParameter("email"));
/*  52 */           user.setOfficePhone(arg0.getParameter("officePhone"));
/*  53 */           user.setMobilePhone(arg0.getParameter("mobilePhone"));
/*  54 */           TSDepart depart = new TSDepart();
/*  55 */           depart.setId(arg0.getParameter("TSDepart.id"));
/*  56 */          // user.setTSDepart(depart);
/*  57 */           user.setRealName(arg0.getParameter("realName"));
/*  58 */           user.setStatus(WorkFlowGlobals.User_Normal);
/*  59 */           user.setActivitiSync(Short.valueOf(activitiSync));
/*     */           try {
/*  61 */             this.activitiService.save(user, roleid, user.getActivitiSync());
/*  62 */             this.message = ("添加用户: " + user.getUserName() + "时同步到工作流成功");
/*  63 */             this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */           } catch (Exception e) {
/*  65 */             e.printStackTrace();
/*  66 */             logger.debug(e.getMessage());
/*     */           }
/*  68 */           return;
/*     */         }
/*     */         try
/*     */         {
/*  72 */           TSUser users = (TSUser)this.systemService.getEntity(TSUser.class, userId);
/*  73 */           this.activitiService.save(users, roleid, users.getActivitiSync());
/*  74 */           this.message = ("更新用户: " + users.getUserName() + "时同步到工作流成功");
/*  75 */           this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */         } catch (Exception e) {
/*  77 */           e.printStackTrace();
/*  78 */           logger.debug(e.getMessage());
/*     */         }
/*  80 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2)
/*     */     throws Exception
/*     */   {
/*  94 */     String requestPath = ResourceUtil.getRequestPath(arg0);
/*  95 */     if (!this.includeUrls.contains(requestPath)) {
/*  96 */       return true;
/*     */     }
/*     */ 
/*  99 */     String userId = arg0.getParameter("id");
/* 100 */     if (requestPath.indexOf("userController.do?del") != -1) {
/*     */       try {
/* 102 */         if (StringUtil.isEmpty(userId)) {
/* 103 */           return true;
/*     */         }
/* 105 */         TSUser user = (TSUser)this.systemService.getEntity(TSUser.class, userId);
/* 106 */         this.activitiService.delete(user);
/* 107 */         this.message = ("用户: " + user.getUserName() + "工作流中同步删除成功");
/* 108 */         this.systemService.addLog(this.message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
/*     */       } catch (Exception e) {
/* 110 */         e.printStackTrace();
/* 111 */         logger.debug(e.getMessage());
/*     */       }
/*     */     }
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   public List<String> getIncludeUrls() {
/* 118 */     return this.includeUrls;
/*     */   }
/*     */ 
/*     */   public void setIncludeUrls(List<String> includeUrls) {
/* 122 */     this.includeUrls = includeUrls;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.interceptors.SynUserInterceptor
 * JD-Core Version:    0.6.0
 */