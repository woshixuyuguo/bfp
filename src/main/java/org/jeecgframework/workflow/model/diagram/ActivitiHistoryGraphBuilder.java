/*     */ package org.jeecgframework.workflow.model.diagram;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.history.HistoricActivityInstance;
/*     */ import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
/*     */ import org.activiti.engine.impl.Page;
/*     */ import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
/*     */ import org.activiti.engine.impl.context.Context;
/*     */ import org.activiti.engine.impl.interceptor.CommandContext;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityManager;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntityManager;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.PvmActivity;
/*     */ import org.activiti.engine.impl.pvm.PvmTransition;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class ActivitiHistoryGraphBuilder
/*     */ {
/*  25 */   private static Logger logger = LoggerFactory.getLogger(ActivitiHistoryGraphBuilder.class);
/*     */   private String processInstanceId;
/*     */   private ProcessDefinitionEntity processDefinitionEntity;
/*     */   private List<HistoricActivityInstance> historicActivityInstances;
/*  29 */   private List<HistoricActivityInstance> visitedHistoricActivityInstances = new ArrayList();
/*  30 */   private Map<String, Node> nodeMap = new HashMap();
/*     */ 
/*     */   public ActivitiHistoryGraphBuilder(String processInstanceId) {
/*  33 */     this.processInstanceId = processInstanceId;
/*     */   }
/*     */ 
/*     */   public Graph build() {
/*  37 */     fetchProcessDefinitionEntity();
/*  38 */     fetchHistoricActivityInstances();
/*     */ 
/*  40 */     Graph graph = new Graph();
/*     */ 
/*  42 */     for (HistoricActivityInstance historicActivityInstance : this.historicActivityInstances) {
/*  43 */       Node currentNode = new Node();
/*  44 */       currentNode.setId(historicActivityInstance.getId());
/*  45 */       currentNode.setName(historicActivityInstance.getActivityId());
/*  46 */       currentNode.setType(historicActivityInstance.getActivityType());
/*  47 */       currentNode
/*  48 */         .setActive(historicActivityInstance.getEndTime() == null);
/*  49 */       logger.debug("currentNode : {}:{}", currentNode.getName(), 
/*  50 */         currentNode.getId());
/*     */ 
/*  52 */       Edge previousEdge = findPreviousEdge(currentNode, 
/*  53 */         historicActivityInstance.getStartTime().getTime());
/*     */ 
/*  55 */       if (previousEdge == null)
/*     */       {
/*  61 */         graph.setInitial(currentNode);
/*     */       }
/*  63 */       else logger.debug("previousEdge : {}", previousEdge.getName());
/*     */ 
/*  66 */       this.nodeMap.put(currentNode.getId(), currentNode);
/*  67 */       this.visitedHistoricActivityInstances.add(historicActivityInstance);
/*     */     }
/*     */ 
/*  70 */     if (graph.getInitial() == null) {
/*  71 */       throw new IllegalStateException("cannot find initial.");
/*     */     }
/*     */ 
/*  74 */     return graph;
/*     */   }
/*     */ 
/*     */   public void fetchProcessDefinitionEntity() {
/*  78 */     String processDefinitionId = Context.getCommandContext()
/*  79 */       .getHistoricProcessInstanceEntityManager()
/*  80 */       .findHistoricProcessInstance(this.processInstanceId)
/*  81 */       .getProcessDefinitionId();
/*  82 */     GetDeploymentProcessDefinitionCmd cmd = new GetDeploymentProcessDefinitionCmd(
/*  83 */       processDefinitionId);
/*  84 */     this.processDefinitionEntity = cmd.execute(Context.getCommandContext());
/*     */   }
/*     */ 
/*     */   public void fetchHistoricActivityInstances() {
/*  88 */     HistoricActivityInstanceQueryImpl historicActivityInstanceQueryImpl = new HistoricActivityInstanceQueryImpl();
/*     */ 
/*  93 */     historicActivityInstanceQueryImpl.processInstanceId(this.processInstanceId)
/*  94 */       .orderByHistoricActivityInstanceId().asc();
/*     */ 
/*  96 */     Page page = new Page(0, 100);
/*  97 */     this.historicActivityInstances = 
/*  98 */       Context.getCommandContext()
/*  99 */       .getHistoricActivityInstanceEntityManager()
/* 100 */       .findHistoricActivityInstancesByQueryCriteria(
/* 101 */       historicActivityInstanceQueryImpl, page);
/*     */   }
/*     */ 
/*     */   public Edge findPreviousEdge(Node currentNode, long currentStartTime) {
/* 105 */     String activityId = currentNode.getName();
/* 106 */     ActivityImpl activityImpl = this.processDefinitionEntity
/* 107 */       .findActivity(activityId);
/* 108 */     HistoricActivityInstance nestestHistoricActivityInstance = null;
/* 109 */     String temporaryPvmTransitionId = null;
/*     */ 
/* 113 */     Iterator localIterator = activityImpl
/* 113 */       .getIncomingTransitions().iterator();
/*     */ 
/* 112 */     while (localIterator.hasNext()) {
/* 113 */       PvmTransition pvmTransition = (PvmTransition)localIterator.next();
/* 114 */       PvmActivity source = pvmTransition.getSource();
/*     */ 
/* 116 */       String previousActivityId = source.getId();
/*     */ 
/* 118 */       HistoricActivityInstance visitiedHistoryActivityInstance = 
/* 119 */         findVisitedHistoricActivityInstance(previousActivityId);
/*     */ 
/* 121 */       if (visitiedHistoryActivityInstance == null)
/*     */       {
/*     */         continue;
/*     */       }
/*     */ 
/* 126 */       if (visitiedHistoryActivityInstance.getEndTime() == null)
/*     */       {
/*     */         continue;
/*     */       }
/* 130 */       logger.debug("current activity start time : {}", 
/* 131 */         new Date(currentStartTime));
/* 132 */       logger.debug("nestest activity end time : {}", 
/* 133 */         visitiedHistoryActivityInstance.getEndTime());
/*     */ 
/* 137 */       if (currentStartTime < visitiedHistoryActivityInstance.getEndTime()
/* 137 */         .getTime())
/*     */       {
/*     */         continue;
/*     */       }
/* 141 */       if (nestestHistoricActivityInstance == null) {
/* 142 */         nestestHistoricActivityInstance = visitiedHistoryActivityInstance;
/* 143 */         temporaryPvmTransitionId = pvmTransition.getId();
/*     */       } else {
/* 145 */         if (currentStartTime - nestestHistoricActivityInstance
/* 145 */           .getEndTime().getTime() <= currentStartTime - visitiedHistoryActivityInstance
/* 146 */           .getEndTime().getTime()) {
/*     */           continue;
/*     */         }
/* 149 */         nestestHistoricActivityInstance = visitiedHistoryActivityInstance;
/* 150 */         temporaryPvmTransitionId = pvmTransition.getId();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 155 */     if (nestestHistoricActivityInstance == null) {
/* 156 */       return null;
/*     */     }
/*     */ 
/* 159 */     Node previousNode = 
/* 160 */       (Node)this.nodeMap
/* 160 */       .get(nestestHistoricActivityInstance.getId());
/*     */ 
/* 162 */     if (previousNode == null) {
/* 163 */       return null;
/*     */     }
/*     */ 
/* 166 */     logger.debug("previousNode : {}:{}", previousNode.getName(), 
/* 167 */       previousNode.getId());
/*     */ 
/* 169 */     Edge edge = new Edge();
/* 170 */     edge.setName(temporaryPvmTransitionId);
/* 171 */     previousNode.getEdges().add(edge);
/* 172 */     edge.setSrc(previousNode);
/* 173 */     edge.setDest(currentNode);
/*     */ 
/* 175 */     return edge;
/*     */   }
/*     */ 
/*     */   public HistoricActivityInstance findVisitedHistoricActivityInstance(String activityId)
/*     */   {
/* 180 */     for (int i = this.visitedHistoricActivityInstances.size() - 1; i >= 0; i--) {
/* 181 */       HistoricActivityInstance historicActivityInstance = 
/* 182 */         (HistoricActivityInstance)this.visitedHistoricActivityInstances
/* 182 */         .get(i);
/*     */ 
/* 184 */       if (activityId.equals(historicActivityInstance.getActivityId())) {
/* 185 */         return historicActivityInstance;
/*     */       }
/*     */     }
/*     */ 
/* 189 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.ActivitiHistoryGraphBuilder
 * JD-Core Version:    0.6.0
 */