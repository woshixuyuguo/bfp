/*     */ package org.jeecgframework.workflow.pojo.activiti;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.hibernate.annotations.GenericGenerator;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="act_id_user")
/*     */ public class ActIdUser
/*     */   implements Serializable
/*     */ {
/*     */   private String id;
/*     */   private Integer rev;
/*     */   private String first;
/*     */   private String last;
/*     */   private String email;
/*     */   private String pwd;
/*     */   private String pictureId;
/*  32 */   private Set<ActIdMembership> actIdMemberships = new HashSet(0);
/*     */ 
/*  39 */   @Id
/*     */   @GeneratedValue(generator="hibernate-uuid")
/*     */   @GenericGenerator(name="hibernate-uuid", strategy="uuid")
/*     */   @Column(name="id_")
/*     */   public String getId() { return this.id; }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  43 */     this.id = id;
/*     */   }
/*     */   @Column(name="rev_")
/*     */   public Integer getRev() {
/*  48 */     return this.rev;
/*     */   }
/*     */ 
/*     */   public void setRev(Integer rev) {
/*  52 */     this.rev = rev;
/*     */   }
/*     */   @Column(name="first_")
/*     */   public String getFirst() {
/*  57 */     return this.first;
/*     */   }
/*     */ 
/*     */   public void setFirst(String first) {
/*  61 */     this.first = first;
/*     */   }
/*     */   @Column(name="last_")
/*     */   public String getLast() {
/*  66 */     return this.last;
/*     */   }
/*     */ 
/*     */   public void setLast(String last) {
/*  70 */     this.last = last;
/*     */   }
/*     */   @Column(name="email_")
/*     */   public String getEmail() {
/*  75 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email) {
/*  79 */     this.email = email;
/*     */   }
/*     */   @Column(name="pwd_")
/*     */   public String getPwd() {
/*  84 */     return this.pwd;
/*     */   }
/*     */ 
/*     */   public void setPwd(String pwd) {
/*  88 */     this.pwd = pwd;
/*     */   }
/*     */   @Column(name="picture_id_", length=64)
/*     */   public String getPictureId() {
/*  93 */     return this.pictureId;
/*     */   }
/*     */ 
/*     */   public void setPictureId(String pictureId) {
/*  97 */     this.pictureId = pictureId;
/*     */   }
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="actIdUser")
/*     */   public Set<ActIdMembership> getActIdMemberships() {
/* 102 */     return this.actIdMemberships;
/*     */   }
/*     */ 
/*     */   public void setActIdMemberships(Set<ActIdMembership> actIdMemberships) {
/* 106 */     this.actIdMemberships = actIdMemberships;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.activiti.ActIdUser
 * JD-Core Version:    0.6.0
 */