/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
/*    */ import org.activiti.engine.impl.context.Context;
/*    */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*    */ import org.activiti.engine.impl.pvm.PvmActivity;
/*    */ import org.activiti.engine.impl.pvm.PvmTransition;
/*    */ 
/*    */ public class ActivitiGraphBuilder
/*    */ {
/*    */   private String processDefinitionId;
/*    */   private ProcessDefinitionEntity processDefinitionEntity;
/* 15 */   private Set<String> visitedNodeIds = new HashSet();
/*    */ 
/*    */   public ActivitiGraphBuilder(String processDefinitionId) {
/* 18 */     this.processDefinitionId = processDefinitionId;
/*    */   }
/*    */ 
/*    */   public Graph build() {
/* 22 */     fetchProcessDefinitionEntity();
/*    */ 
/* 24 */     Node initial = visitNode(this.processDefinitionEntity.getInitial());
/*    */ 
/* 26 */     Graph graph = new Graph();
/* 27 */     graph.setInitial(initial);
/*    */ 
/* 29 */     return graph;
/*    */   }
/*    */ 
/*    */   public void fetchProcessDefinitionEntity() {
/* 33 */     GetDeploymentProcessDefinitionCmd cmd = new GetDeploymentProcessDefinitionCmd(
/* 34 */       this.processDefinitionId);
/* 35 */     this.processDefinitionEntity = cmd.execute(Context.getCommandContext());
/*    */   }
/*    */ 
/*    */   public Node visitNode(PvmActivity pvmActivity) {
/* 39 */     if (this.visitedNodeIds.contains(pvmActivity.getId())) {
/* 40 */       return null;
/*    */     }
/*    */ 
/* 43 */     this.visitedNodeIds.add(pvmActivity.getId());
/*    */ 
/* 45 */     Node currentNode = new Node();
/* 46 */     currentNode.setId(pvmActivity.getId());
/* 47 */     currentNode.setName(getString(pvmActivity.getProperty("name")));
/* 48 */     currentNode.setType(getString(pvmActivity.getProperty("type")));
/*    */ 
/* 50 */     for (PvmTransition pvmTransition : pvmActivity.getOutgoingTransitions()) {
/* 51 */       PvmActivity destination = pvmTransition.getDestination();
/* 52 */       Node targetNode = visitNode(destination);
/*    */ 
/* 54 */       if (targetNode == null)
/*    */       {
/*    */         continue;
/*    */       }
/* 58 */       Edge edge = new Edge();
/* 59 */       edge.setId(pvmTransition.getId());
/* 60 */       edge.setSrc(currentNode);
/* 61 */       edge.setDest(targetNode);
/* 62 */       currentNode.getEdges().add(edge);
/*    */     }
/*    */ 
/* 65 */     return currentNode;
/*    */   }
/*    */ 
/*    */   public String getString(Object object) {
/* 69 */     if (object == null)
/* 70 */       return null;
/* 71 */     if ((object instanceof String)) {
/* 72 */       return (String)object;
/*    */     }
/* 74 */     return object.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.ActivitiGraphBuilder
 * JD-Core Version:    0.6.0
 */