/*     */ package org.jeecgframework.workflow.pojo.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import org.jeecgframework.core.common.entity.IdEntity;
/*     */ import org.jeecgframework.web.system.pojo.base.TSType;
/*     */ import org.jeecgframework.web.system.pojo.base.TSUser;
/*     */ 
/*     */ @Entity
/*     */ @Table(name="t_p_process")
/*     */ public class TPProcess extends IdEntity
/*     */   implements Serializable
/*     */ {
/*     */   private TSType TSType;
/*     */   private TSUser TSUser;
/*     */   private String processname;
/*     */   private Short processstate;
/*     */   private String processxmlpath;
/*     */   private byte[] processxml;
/*     */   private String processkey;
/*     */   private String note;
/*  35 */   private Set<TPProcesspro> TPProcesspros = new HashSet(0);
/*  36 */   private List<TPProcessnode> TPProcessnodes = new ArrayList(0);
/*     */ 
/*  42 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="typeid")
/*     */   public TSType getTSType() { return this.TSType; }
/*     */ 
/*     */   public void setTSType(TSType TSType)
/*     */   {
/*  46 */     this.TSType = TSType;
/*     */   }
/*  52 */   @ManyToOne(fetch=FetchType.LAZY)
/*     */   @JoinColumn(name="userid")
/*     */   public TSUser getTSUser() { return this.TSUser; }
/*     */ 
/*     */   public void setTSUser(TSUser TSUser)
/*     */   {
/*  56 */     this.TSUser = TSUser;
/*     */   }
/*     */   @Column(name="processname", length=50)
/*     */   public String getProcessname() {
/*  61 */     return this.processname;
/*     */   }
/*     */ 
/*     */   public void setProcessname(String processname) {
/*  65 */     this.processname = processname;
/*     */   }
/*     */   @Column(name="processstate")
/*     */   public Short getProcessstate() {
/*  70 */     return this.processstate;
/*     */   }
/*     */ 
/*     */   public void setProcessstate(Short processstate) {
/*  74 */     this.processstate = processstate;
/*     */   }
/*     */   @Column(name="processxmlpath", length=100)
/*     */   public String getProcessxmlpath() {
/*  79 */     return this.processxmlpath;
/*     */   }
/*     */ 
/*     */   public void setProcessxmlpath(String processxmlpath) {
/*  83 */     this.processxmlpath = processxmlpath;
/*     */   }
/*     */   @Column(name="processxml")
/*     */   public byte[] getProcessxml() {
/*  88 */     return this.processxml;
/*     */   }
/*     */ 
/*     */   public void setProcessxml(byte[] processxml) {
/*  92 */     this.processxml = processxml;
/*     */   }
/*     */   @Column(name="processkey", length=100)
/*     */   public String getProcesskey() {
/*  97 */     return this.processkey;
/*     */   }
/*     */ 
/*     */   public void setProcesskey(String processkey) {
/* 101 */     this.processkey = processkey;
/*     */   }
/*     */   @Column(name="note", length=200)
/*     */   public String getNote() {
/* 106 */     return this.note;
/*     */   }
/*     */ 
/*     */   public void setNote(String note) {
/* 110 */     this.note = note;
/*     */   }
/* 114 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcess")
/*     */   public Set<TPProcesspro> getTPProcesspros() { return this.TPProcesspros; }
/*     */ 
/*     */   public void setTPProcesspros(Set<TPProcesspro> TPProcesspros)
/*     */   {
/* 118 */     this.TPProcesspros = TPProcesspros;
/*     */   }
/* 122 */   @OneToMany(cascade={javax.persistence.CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="TPProcess")
/*     */   public List<TPProcessnode> getTPProcessnodes() { return this.TPProcessnodes; }
/*     */ 
/*     */   public void setTPProcessnodes(List<TPProcessnode> TPProcessnodes)
/*     */   {
/* 126 */     this.TPProcessnodes = TPProcessnodes;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.pojo.base.TPProcess
 * JD-Core Version:    0.6.0
 */