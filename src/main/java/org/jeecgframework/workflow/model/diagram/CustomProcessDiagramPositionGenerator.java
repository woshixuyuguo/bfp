/*     */ package org.jeecgframework.workflow.model.diagram;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.engine.history.HistoricActivityInstance;
/*     */ import org.activiti.engine.history.HistoricProcessInstance;
/*     */ import org.activiti.engine.impl.HistoricActivityInstanceQueryImpl;
/*     */ import org.activiti.engine.impl.Page;
/*     */ import org.activiti.engine.impl.cmd.GetBpmnModelCmd;
/*     */ import org.activiti.engine.impl.cmd.GetDeploymentProcessDefinitionCmd;
/*     */ import org.activiti.engine.impl.context.Context;
/*     */ import org.activiti.engine.impl.interceptor.CommandContext;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricActivityInstanceEntityManager;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntityManager;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.ResourceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.ResourceEntityManager;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ 
/*     */ public class CustomProcessDiagramPositionGenerator
/*     */ {
/*     */   public static final int OFFSET_SUBPROCESS = 5;
/*     */   public static final int OFFSET_TASK = 20;
/*  31 */   private static List<String> taskType = new ArrayList();
/*  32 */   private static List<String> eventType = new ArrayList();
/*  33 */   private static List<String> gatewayType = new ArrayList();
/*  34 */   private static List<String> subProcessType = new ArrayList();
/*     */ 
/*     */   public CustomProcessDiagramPositionGenerator() {
/*  37 */     init();
/*     */   }
/*     */ 
/*     */   protected static void init() {
/*  41 */     taskType.add("manualTask");
/*  42 */     taskType.add("receiveTask");
/*  43 */     taskType.add("scriptTask");
/*  44 */     taskType.add("sendTask");
/*  45 */     taskType.add("serviceTask");
/*  46 */     taskType.add("userTask");
/*     */ 
/*  48 */     gatewayType.add("exclusiveGateway");
/*  49 */     gatewayType.add("inclusiveGateway");
/*  50 */     gatewayType.add("eventBasedGateway");
/*  51 */     gatewayType.add("parallelGateway");
/*     */ 
/*  53 */     eventType.add("intermediateTimer");
/*  54 */     eventType.add("intermediateMessageCatch");
/*  55 */     eventType.add("intermediateSignalCatch");
/*  56 */     eventType.add("intermediateSignalThrow");
/*  57 */     eventType.add("messageStartEvent");
/*  58 */     eventType.add("startTimerEvent");
/*  59 */     eventType.add("error");
/*  60 */     eventType.add("startEvent");
/*  61 */     eventType.add("errorEndEvent");
/*  62 */     eventType.add("endEvent");
/*     */ 
/*  64 */     subProcessType.add("subProcess");
/*  65 */     subProcessType.add("callActivity");
/*     */   }
/*     */ 
/*     */   public List generateDiagram(String processInstanceId) throws IOException
/*     */   {
/*  70 */     HistoricProcessInstance historicProcessInstance = 
/*  71 */       Context.getCommandContext().getHistoricProcessInstanceEntityManager()
/*  72 */       .findHistoricProcessInstance(processInstanceId);
/*  73 */     String processDefinitionId = historicProcessInstance
/*  74 */       .getProcessDefinitionId();
/*  75 */     GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
/*  76 */       processDefinitionId);
/*  77 */     BpmnModel bpmnModel = getBpmnModelCmd.execute(
/*  78 */       Context.getCommandContext());
/*     */ 
/*  80 */     bpmnModel.getLocationMap();
/*  81 */     ProcessDefinitionEntity definition = new GetDeploymentProcessDefinitionCmd(
/*  82 */       processDefinitionId).execute(Context.getCommandContext());
/*  83 */     String diagramResourceName = definition.getDiagramResourceName();
/*  84 */     String deploymentId = definition.getDeploymentId();
/*  85 */     byte[] bytes = 
/*  86 */       Context.getCommandContext()
/*  87 */       .getResourceEntityManager()
/*  88 */       .findResourceByDeploymentIdAndResourceName(deploymentId, 
/*  89 */       diagramResourceName).getBytes();
/*  90 */     InputStream originDiagram = new ByteArrayInputStream(bytes);
/*  91 */     BufferedImage image = ImageIO.read(originDiagram);
/*     */ 
/*  93 */     HistoricActivityInstanceQueryImpl historicActivityInstanceQueryImpl = new HistoricActivityInstanceQueryImpl();
/*  94 */     historicActivityInstanceQueryImpl.processInstanceId(processInstanceId)
/*  95 */       .orderByHistoricActivityInstanceStartTime().asc();
/*     */ 
/*  97 */     Page page = new Page(0, 100);
/*  98 */     List<HistoricActivityInstance> activityInstances = 
/*  99 */       Context.getCommandContext()
/* 100 */       .getHistoricActivityInstanceEntityManager()
/* 101 */       .findHistoricActivityInstancesByQueryCriteria(
/* 102 */       historicActivityInstanceQueryImpl, page);
/* 103 */     List listResult = new ArrayList();
/*     */ 
/* 105 */     for (HistoricActivityInstance historicActivityInstance : activityInstances) {
/* 106 */       String historicActivityId = historicActivityInstance.getActivityId();
/* 107 */       String activityTypeName = historicActivityInstance.getActivityType();
/* 108 */       ActivityImpl activity = definition.findActivity(historicActivityId);
/* 109 */       if (activity == null)
/*     */         continue;
/* 111 */       if ((historicActivityInstance.getEndTime() == null) || (!activityTypeName.equals("userTask")))
/*     */         continue;
/* 113 */       listResult.add(activity);
/*     */     }
/*     */ 
/* 119 */     return listResult;
/*     */   }
/*     */ 
/*     */   private static String getDiagramExtension(String diagramResourceName) {
/* 123 */     return FilenameUtils.getExtension(diagramResourceName);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.CustomProcessDiagramPositionGenerator
 * JD-Core Version:    0.6.0
 */