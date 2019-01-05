/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Node extends GraphElement
/*    */ {
/*    */   private String type;
/*    */   private boolean active;
/*  9 */   private List<Edge> edges = new ArrayList();
/*    */ 
/*    */   public String getType() {
/* 12 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 16 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public boolean isActive() {
/* 20 */     return this.active;
/*    */   }
/*    */ 
/*    */   public void setActive(boolean active) {
/* 24 */     this.active = active;
/*    */   }
/*    */ 
/*    */   public List<Edge> getEdges() {
/* 28 */     return this.edges;
/*    */   }
/*    */ 
/*    */   public void setEdges(List<Edge> edges) {
/* 32 */     this.edges = edges;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.Node
 * JD-Core Version:    0.6.0
 */