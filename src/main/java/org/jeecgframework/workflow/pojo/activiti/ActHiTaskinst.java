/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.sql.Timestamp;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Transient;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_hi_taskinst")
/*     */ public class ActHiTaskinst
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private String procDefId;
/*     */   private String taskDefKey;
/*     */   private String procInstId;
/*     */   private String executionId;
/*     */   private String name;
/*     */   private String parentTaskId;
/*     */   private String description;
/*     */   private String owner;
/*     */   private String assignee;
/*     */   private Timestamp startTime;
/*     */   private Timestamp endTime;
/*     */   private Long duration;
/*     */   private String deleteReason;
/*     */   private Integer priority;
/*     */   private Timestamp dueDate;
/*     */   private ActReProcdef prodef;
/*     */   private ActHiProcinst proInsl;
/*     */ 
/*     */   @Id
/*     */   @Column(name="id_")
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   public String getId()
/*     */   {
/*  49 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id) {
/*  53 */     this.id = id;
/*     */   }
/*     */   @Column(name="proc_def_id_", length=64)
/*     */   public String getProcDefId() {
/*  58 */     return this.procDefId;
/*     */   }
/*     */ 
/*     */   public void setProcDefId(String procDefId) {
/*  62 */     this.procDefId = procDefId;
/*     */   }
/*     */   @Column(name="task_def_key_")
/*     */   public String getTaskDefKey() {
/*  67 */     return this.taskDefKey;
/*     */   }
/*     */ 
/*     */   public void setTaskDefKey(String taskDefKey) {
/*  71 */     this.taskDefKey = taskDefKey;
/*     */   }
/*     */   @Column(name="proc_inst_id_", length=64)
/*     */   public String getProcInstId() {
/*  76 */     return this.procInstId;
/*     */   }
/*     */ 
/*     */   public void setProcInstId(String procInstId) {
/*  80 */     this.procInstId = procInstId;
/*     */   }
/*     */   @Column(name="execution_id_", length=64)
/*     */   public String getExecutionId() {
/*  85 */     return this.executionId;
/*     */   }
/*     */ 
/*     */   public void setExecutionId(String executionId) {
/*  89 */     this.executionId = executionId;
/*     */   }
/*     */   @Column(name="name_")
/*     */   public String getName() {
/*  94 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  98 */     this.name = name;
/*     */   }
/*     */   @Column(name="parent_task_id_", length=64)
/*     */   public String getParentTaskId() {
/* 103 */     return this.parentTaskId;
/*     */   }
/*     */ 
/*     */   public void setParentTaskId(String parentTaskId) {
/* 107 */     this.parentTaskId = parentTaskId;
/*     */   }
/*     */   @Column(name="description_", length=4000)
/*     */   public String getDescription() {
/* 112 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 116 */     this.description = description;
/*     */   }
/*     */   @Column(name="owner_")
/*     */   public String getOwner() {
/* 121 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(String owner) {
/* 125 */     this.owner = owner;
/*     */   }
/*     */   @Column(name="assignee_")
/*     */   public String getAssignee() {
/* 130 */     return this.assignee;
/*     */   }
/*     */ 
/*     */   public void setAssignee(String assignee) {
/* 134 */     this.assignee = assignee;
/*     */   }
/*     */   @Column(name="start_time_", nullable=false, length=29)
/*     */   public Timestamp getStartTime() {
/* 139 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Timestamp startTime) {
/* 143 */     this.startTime = startTime;
/*     */   }
/*     */   @Column(name="end_time_", length=29)
/*     */   public Timestamp getEndTime() {
/* 148 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Timestamp endTime) {
/* 152 */     this.endTime = endTime;
/*     */   }
/*     */   @Column(name="duration_")
/*     */   public Long getDuration() {
/* 157 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(Long duration) {
/* 161 */     this.duration = duration;
/*     */   }
/*     */   @Column(name="delete_reason_", length=4000)
/*     */   public String getDeleteReason() {
/* 166 */     return this.deleteReason;
/*     */   }
/*     */ 
/*     */   public void setDeleteReason(String deleteReason) {
/* 170 */     this.deleteReason = deleteReason;
/*     */   }
/*     */   @Column(name="priority_")
/*     */   public Integer getPriority() {
/* 175 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority) {
/* 179 */     this.priority = priority;
/*     */   }
/*     */   @Column(name="due_date_", length=29)
/*     */   public Timestamp getDueDate() {
/* 184 */     return this.dueDate;
/*     */   }
/*     */ 
/*     */   public void setDueDate(Timestamp dueDate) {
/* 188 */     this.dueDate = dueDate; } 
/* 193 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_def_id_", insertable=false, updatable=false)
/*     */   public ActReProcdef getProdef() { return this.prodef; }
/*     */ 
/*     */   public void setProdef(ActReProcdef prodef)
/*     */   {
/* 197 */     this.prodef = prodef; } 
/* 202 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="proc_inst_id_", insertable=false, updatable=false)
/*     */   public ActHiProcinst getProInsl() { return this.proInsl; }
/*     */ 
/*     */   public void setProInsl(ActHiProcinst proInsl)
/*     */   {
/* 206 */     this.proInsl = proInsl;
/*     */   }
/*     */ 
/*     */   public String dealTimeFromNum(long time)
/*     */   {
/* 214 */     StringBuilder result = new StringBuilder();
/* 215 */     long interval = time / 1000L;
/* 216 */     long day = 86400L;
/* 217 */     long hour = 3600L;
/* 218 */     long minute = 60L;
/* 219 */     int detailDay = 0;
/* 220 */     int detailHour = 0;
/* 221 */     int detailMinute = 0;
/* 222 */     int detailSecond = 0;
/* 223 */     if (interval >= 86400L) {
/* 224 */       detailDay = (int)(interval / 86400L);
/* 225 */       interval -= detailDay * 86400L;
/*     */     }
/* 227 */     if (interval >= 3600L) {
/* 228 */       detailHour = (int)(interval / 3600L);
/* 229 */       interval -= 3600L * detailHour;
/*     */     }
/* 231 */     if (interval >= 60L) {
/* 232 */       detailMinute = (int)(interval / 60L);
/* 233 */       interval -= detailMinute * 60L;
/*     */     }
/* 235 */     if (interval > 0L) {
/* 236 */       detailSecond = (int)interval;
/*     */     }
/* 238 */     result.setLength(0);
/* 239 */     if (detailDay > 0) {
/* 240 */       result.append(detailDay);
/* 241 */       result.append("天");
/*     */     }
/* 243 */     if (detailHour > 0) {
/* 244 */       result.append(detailHour);
/* 245 */       result.append("小时");
/*     */     }
/* 247 */     if (detailMinute > 0) {
/* 248 */       result.append(detailMinute);
/* 249 */       result.append("分");
/*     */     }
/* 251 */     if (detailSecond > 0) {
/* 252 */       result.append(detailSecond);
/* 253 */       result.append("秒");
/*     */     }
/* 255 */     return result.toString();
/*     */   }
/* 259 */   @Transient
/*     */   public String getDurationStr() { return dealTimeFromNum(this.duration.longValue());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActHiTaskinst
 * JD-Core Version:    0.6.0
 */