/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Graph
/*    */ {
/*    */   private Node initial;
/*    */ 
/*    */   public Node getInitial()
/*    */   {
/* 10 */     return this.initial;
/*    */   }
/*    */ 
/*    */   public void setInitial(Node initial) {
/* 14 */     this.initial = initial;
/*    */   }
/*    */ 
/*    */   public List<Node> getNodes() {
/* 18 */     List nodes = new ArrayList();
/* 19 */     visitNode(this.initial, nodes);
/*    */ 
/* 21 */     return nodes;
/*    */   }
/*    */ 
/*    */   public void visitNode(Node node, List<Node> nodes) {
/* 25 */     nodes.add(node);
/*    */ 
/* 27 */     for (Edge edge : node.getEdges()) {
/* 28 */       Node nextNode = edge.getDest();
/* 29 */       visitNode(nextNode, nodes);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<Edge> getEdges() {
/* 34 */     List edges = new ArrayList();
/* 35 */     visitEdge(this.initial, edges);
/*    */ 
/* 37 */     return edges;
/*    */   }
/*    */ 
/*    */   public void visitEdge(Node node, List<Edge> edges) {
/* 41 */     for (Edge edge : node.getEdges()) {
/* 42 */       edges.add(edge);
/*    */ 
/* 44 */       Node nextNode = edge.getDest();
/* 45 */       visitEdge(nextNode, edges);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Node findById(String id) {
/* 50 */     for (Node node : getNodes()) {
/* 51 */       if (id.equals(node.getId())) {
/* 52 */         return node;
/*    */       }
/*    */     }
/*    */ 
/* 56 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.Graph
 * JD-Core Version:    0.6.0
 */