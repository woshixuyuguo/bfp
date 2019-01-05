/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ public class Edge extends GraphElement
/*    */ {
/*    */   private Node src;
/*    */   private Node dest;
/*    */ 
/*    */   public Node getSrc()
/*    */   {
/*  8 */     return this.src;
/*    */   }
/*    */ 
/*    */   public void setSrc(Node src) {
/* 12 */     this.src = src;
/*    */   }
/*    */ 
/*    */   public Node getDest() {
/* 16 */     return this.dest;
/*    */   }
/*    */ 
/*    */   public void setDest(Node dest) {
/* 20 */     this.dest = dest;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.Edge
 * JD-Core Version:    0.6.0
 */