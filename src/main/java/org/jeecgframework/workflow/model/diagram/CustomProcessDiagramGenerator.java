/*     */ package org.jeecgframework.workflow.model.diagram;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Path2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.activiti.bpmn.model.Artifact;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.bpmn.model.FlowElement;
/*     */ import org.activiti.bpmn.model.FlowElementsContainer;
/*     */ import org.activiti.bpmn.model.FlowNode;
/*     */ import org.activiti.bpmn.model.GraphicInfo;
/*     */ import org.activiti.bpmn.model.Lane;
/*     */ import org.activiti.bpmn.model.Pool;
/*     */ import org.activiti.bpmn.model.Process;
/*     */ import org.activiti.bpmn.model.SequenceFlow;
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
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityManager;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.ResourceEntity;
/*     */ import org.activiti.engine.impl.persistence.entity.ResourceEntityManager;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ 
/*     */ public class CustomProcessDiagramGenerator
/*     */ {
/*     */   public static final int OFFSET_SUBPROCESS = 5;
/*     */   public static final int OFFSET_TASK = 20;
/*  52 */   private static List<String> taskType = new ArrayList();
/*  53 */   private static List<String> eventType = new ArrayList();
/*  54 */   private static List<String> gatewayType = new ArrayList();
/*  55 */   private static List<String> subProcessType = new ArrayList();
/*  56 */   private static Color RUNNING_COLOR = Color.RED;
/*  57 */   private static Color HISTORY_COLOR = Color.GREEN;
/*  58 */   private static Color SKIP_COLOR = Color.GRAY;
/*  59 */   private static Stroke THICK_BORDER_STROKE = new BasicStroke(3.0F);
/*     */   private int minX;
/*     */   private int minY;
/*     */ 
/*     */   public CustomProcessDiagramGenerator()
/*     */   {
/*  64 */     init();
/*     */   }
/*     */ 
/*     */   protected static void init() {
/*  68 */     taskType.add("manualTask");
/*  69 */     taskType.add("receiveTask");
/*  70 */     taskType.add("scriptTask");
/*  71 */     taskType.add("sendTask");
/*  72 */     taskType.add("serviceTask");
/*  73 */     taskType.add("userTask");
/*     */ 
/*  75 */     gatewayType.add("exclusiveGateway");
/*  76 */     gatewayType.add("inclusiveGateway");
/*  77 */     gatewayType.add("eventBasedGateway");
/*  78 */     gatewayType.add("parallelGateway");
/*     */ 
/*  80 */     eventType.add("intermediateTimer");
/*  81 */     eventType.add("intermediateMessageCatch");
/*  82 */     eventType.add("intermediateSignalCatch");
/*  83 */     eventType.add("intermediateSignalThrow");
/*  84 */     eventType.add("messageStartEvent");
/*  85 */     eventType.add("startTimerEvent");
/*  86 */     eventType.add("error");
/*  87 */     eventType.add("startEvent");
/*  88 */     eventType.add("errorEndEvent");
/*  89 */     eventType.add("endEvent");
/*     */ 
/*  91 */     subProcessType.add("subProcess");
/*  92 */     subProcessType.add("callActivity");
/*     */   }
/*     */ 
/*     */   public InputStream generateDiagram(String processInstanceId) throws IOException
/*     */   {
/*  97 */     HistoricProcessInstance historicProcessInstance = 
/*  98 */       Context.getCommandContext().getHistoricProcessInstanceEntityManager()
/*  99 */       .findHistoricProcessInstance(processInstanceId);
/* 100 */     String processDefinitionId = historicProcessInstance
/* 101 */       .getProcessDefinitionId();
/* 102 */     GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
/* 103 */       processDefinitionId);
/* 104 */     BpmnModel bpmnModel = getBpmnModelCmd.execute(
/* 105 */       Context.getCommandContext());
/* 106 */     Point point = getMinXAndMinY(bpmnModel);
/* 107 */     this.minX = point.x;
/* 108 */     this.minY = point.y;
/* 109 */     this.minX = (this.minX <= 5 ? 5 : this.minX);
/* 110 */     this.minY = (this.minY <= 5 ? 5 : this.minY);
/* 111 */     this.minX -= 5;
/* 112 */     this.minY -= 5;
/*     */ 
/* 114 */     ProcessDefinitionEntity definition = new GetDeploymentProcessDefinitionCmd(
/* 115 */       processDefinitionId).execute(Context.getCommandContext());
/* 116 */     String diagramResourceName = definition.getDiagramResourceName();
/* 117 */     String deploymentId = definition.getDeploymentId();
/* 118 */     byte[] bytes = 
/* 119 */       Context.getCommandContext()
/* 120 */       .getResourceEntityManager()
/* 121 */       .findResourceByDeploymentIdAndResourceName(deploymentId, 
/* 122 */       diagramResourceName).getBytes();
/* 123 */     InputStream originDiagram = new ByteArrayInputStream(bytes);
/* 124 */     BufferedImage image = ImageIO.read(originDiagram);
/*     */ 
/* 126 */     HistoricActivityInstanceQueryImpl historicActivityInstanceQueryImpl = new HistoricActivityInstanceQueryImpl();
/* 127 */     historicActivityInstanceQueryImpl.processInstanceId(processInstanceId)
/* 128 */       .orderByHistoricActivityInstanceStartTime().asc();
/*     */ 
/* 130 */     Page page = new Page(0, 100);
/* 131 */     List<HistoricActivityInstance> activityInstances = 
/* 132 */       Context.getCommandContext()
/* 133 */       .getHistoricActivityInstanceEntityManager()
/* 134 */       .findHistoricActivityInstancesByQueryCriteria(
/* 135 */       historicActivityInstanceQueryImpl, page);
/*     */ 
/* 137 */     drawHistoryFlow(image, processInstanceId);
/*     */ 
/* 139 */     for (HistoricActivityInstance historicActivityInstance : activityInstances) {
/* 140 */       String historicActivityId = historicActivityInstance
/* 141 */         .getActivityId();
/* 142 */       ActivityImpl activity = definition.findActivity(historicActivityId);
/*     */ 
/* 144 */       if (activity != null) {
/* 145 */         if (historicActivityInstance.getEndTime() == null)
/*     */         {
/* 147 */           signRunningNode(image, activity.getX() - this.minX, 
/* 148 */             activity.getY() - this.minY, activity.getWidth(), 
/* 149 */             activity.getHeight(), 
/* 150 */             historicActivityInstance.getActivityType());
/*     */         } else {
/* 152 */           String deleteReason = null;
/*     */ 
/* 154 */           if (historicActivityInstance.getTaskId() != null) {
/* 155 */             deleteReason = 
/* 156 */               Context.getCommandContext()
/* 157 */               .getHistoricTaskInstanceEntityManager()
/* 158 */               .findHistoricTaskInstanceById(
/* 159 */               historicActivityInstance.getTaskId())
/* 160 */               .getDeleteReason();
/*     */           }
/*     */ 
/* 164 */           if ("跳过".equals(deleteReason))
/* 165 */             signSkipNode(image, activity.getX() - this.minX, 
/* 166 */               activity.getY() - this.minY, 
/* 167 */               activity.getWidth(), activity.getHeight(), 
/* 168 */               historicActivityInstance.getActivityType());
/*     */           else {
/* 170 */             signHistoryNode(image, activity.getX() - this.minX, 
/* 171 */               activity.getY() - this.minY, 
/* 172 */               activity.getWidth(), activity.getHeight(), 
/* 173 */               historicActivityInstance.getActivityType());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 179 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 180 */     String formatName = getDiagramExtension(diagramResourceName);
/* 181 */     ImageIO.write(image, formatName, out);
/*     */ 
/* 183 */     return new ByteArrayInputStream(out.toByteArray());
/*     */   }
/*     */ 
/*     */   private static String getDiagramExtension(String diagramResourceName) {
/* 187 */     return FilenameUtils.getExtension(diagramResourceName);
/*     */   }
/*     */ 
/*     */   private static void signRunningNode(BufferedImage image, int x, int y, int width, int height, String activityType)
/*     */   {
/* 208 */     Color nodeColor = RUNNING_COLOR;
/* 209 */     Graphics2D graphics = image.createGraphics();
/*     */     try
/*     */     {
/* 212 */       drawNodeBorder(x, y, width, height, graphics, nodeColor, 
/* 213 */         activityType);
/*     */     } finally {
/* 215 */       graphics.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void signHistoryNode(BufferedImage image, int x, int y, int width, int height, String activityType)
/*     */   {
/* 237 */     Color nodeColor = HISTORY_COLOR;
/* 238 */     Graphics2D graphics = image.createGraphics();
/*     */     try
/*     */     {
/* 241 */       drawNodeBorder(x, y, width, height, graphics, nodeColor, 
/* 242 */         activityType);
/*     */     } finally {
/* 244 */       graphics.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void signSkipNode(BufferedImage image, int x, int y, int width, int height, String activityType)
/*     */   {
/* 250 */     Color nodeColor = SKIP_COLOR;
/* 251 */     Graphics2D graphics = image.createGraphics();
/*     */     try
/*     */     {
/* 254 */       drawNodeBorder(x, y, width, height, graphics, nodeColor, 
/* 255 */         activityType);
/*     */     } finally {
/* 257 */       graphics.dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void drawNodeBorder(int x, int y, int width, int height, Graphics2D graphics, Color color, String activityType)
/*     */   {
/* 281 */     graphics.setPaint(color);
/* 282 */     graphics.setStroke(THICK_BORDER_STROKE);
/*     */ 
/* 284 */     if (taskType.contains(activityType))
/* 285 */       drawTask(x, y, width, height, graphics);
/* 286 */     else if (gatewayType.contains(activityType))
/* 287 */       drawGateway(x, y, width, height, graphics);
/* 288 */     else if (eventType.contains(activityType))
/* 289 */       drawEvent(x, y, width, height, graphics);
/* 290 */     else if (subProcessType.contains(activityType))
/* 291 */       drawSubProcess(x, y, width, height, graphics);
/*     */   }
/*     */ 
/*     */   protected static void drawTask(int x, int y, int width, int height, Graphics2D graphics)
/*     */   {
/* 300 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, 
/* 301 */       height, 20.0D, 20.0D);
/* 302 */     graphics.draw(rect);
/*     */   }
/*     */ 
/*     */   protected static void drawGateway(int x, int y, int width, int height, Graphics2D graphics)
/*     */   {
/* 310 */     Polygon rhombus = new Polygon();
/* 311 */     rhombus.addPoint(x, y + height / 2);
/* 312 */     rhombus.addPoint(x + width / 2, y + height);
/* 313 */     rhombus.addPoint(x + width, y + height / 2);
/* 314 */     rhombus.addPoint(x + width / 2, y);
/* 315 */     graphics.draw(rhombus);
/*     */   }
/*     */ 
/*     */   protected static void drawEvent(int x, int y, int width, int height, Graphics2D graphics)
/*     */   {
/* 323 */     Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
/* 324 */     graphics.draw(circle);
/*     */   }
/*     */ 
/*     */   protected static void drawSubProcess(int x, int y, int width, int height, Graphics2D graphics)
/*     */   {
/* 332 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x + 1, y + 1, 
/* 333 */       width - 2, height - 2, 5.0D, 5.0D);
/* 334 */     graphics.draw(rect);
/*     */   }
/*     */ 
/*     */   protected Point getMinXAndMinY(BpmnModel bpmnModel)
/*     */   {
/* 339 */     double theMinX = 1.7976931348623157E+308D;
/* 340 */     double theMaxX = 0.0D;
/* 341 */     double theMinY = 1.7976931348623157E+308D;
/* 342 */     double theMaxY = 0.0D;
/*     */     GraphicInfo graphicInfo;
/* 344 */     for (Pool pool : bpmnModel.getPools()) {
/* 345 */       graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
/* 346 */       theMinX = graphicInfo.getX();
/* 347 */       theMaxX = graphicInfo.getX() + graphicInfo.getWidth();
/* 348 */       theMinY = graphicInfo.getY();
/* 349 */       theMaxY = graphicInfo.getY() + graphicInfo.getHeight();
/*     */     }
/*     */ 
/* 352 */     List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
/*     */     GraphicInfo flowNodeGraphicInfo;
/* 354 */     for (FlowNode flowNode : flowNodes) {
/* 355 */       flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode
/* 356 */         .getId());
/*     */ 
/* 359 */       if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > theMaxX) {
/* 360 */         theMaxX = flowNodeGraphicInfo.getX() + 
/* 361 */           flowNodeGraphicInfo.getWidth();
/*     */       }
/*     */ 
/* 364 */       if (flowNodeGraphicInfo.getX() < theMinX) {
/* 365 */         theMinX = flowNodeGraphicInfo.getX();
/*     */       }
/*     */ 
/* 369 */       if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > theMaxY) {
/* 370 */         theMaxY = flowNodeGraphicInfo.getY() + 
/* 371 */           flowNodeGraphicInfo.getHeight();
/*     */       }
/*     */ 
/* 374 */       if (flowNodeGraphicInfo.getY() < theMinY) {
/* 375 */         theMinY = flowNodeGraphicInfo.getY();
/*     */       }
/*     */ 
/* 378 */       for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
/* 379 */         List graphicInfoList = bpmnModel
/* 380 */           .getFlowLocationGraphicInfo(sequenceFlow.getId());
/*     */ 
/* 382 */         for (Iterator localIterator3 = graphicInfoList.iterator(); localIterator3.hasNext(); ) { graphicInfo = (GraphicInfo)localIterator3.next();
/*     */ 
/* 384 */           if (graphicInfo.getX() > theMaxX) {
/* 385 */             theMaxX = graphicInfo.getX();
/*     */           }
/*     */ 
/* 388 */           if (graphicInfo.getX() < theMinX) {
/* 389 */             theMinX = graphicInfo.getX();
/*     */           }
/*     */ 
/* 393 */           if (graphicInfo.getY() > theMaxY) {
/* 394 */             theMaxY = graphicInfo.getY();
/*     */           }
/*     */ 
/* 397 */           if (graphicInfo.getY() < theMinY) {
/* 398 */             theMinY = graphicInfo.getY();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 404 */     List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
/*     */     GraphicInfo artifactGraphicInfo;
/* 406 */     for (Artifact artifact : artifacts) {
/* 407 */       artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact
/* 408 */         .getId());
/*     */ 
/* 410 */       if (artifactGraphicInfo != null)
/*     */       {
/* 413 */         if (artifactGraphicInfo.getX() + artifactGraphicInfo
/* 413 */           .getWidth() > theMaxX) {
/* 414 */           theMaxX = artifactGraphicInfo.getX() + 
/* 415 */             artifactGraphicInfo.getWidth();
/*     */         }
/*     */ 
/* 418 */         if (artifactGraphicInfo.getX() < theMinX) {
/* 419 */           theMinX = artifactGraphicInfo.getX();
/*     */         }
/*     */ 
/* 424 */         if (artifactGraphicInfo.getY() + artifactGraphicInfo
/* 424 */           .getHeight() > theMaxY) {
/* 425 */           theMaxY = artifactGraphicInfo.getY() + 
/* 426 */             artifactGraphicInfo.getHeight();
/*     */         }
/*     */ 
/* 429 */         if (artifactGraphicInfo.getY() < theMinY) {
/* 430 */           theMinY = artifactGraphicInfo.getY();
/*     */         }
/*     */       }
/*     */ 
/* 434 */       List graphicInfoList = bpmnModel
/* 435 */         .getFlowLocationGraphicInfo(artifact.getId());
/*     */ 
/* 437 */       if (graphicInfoList != null) {
/* 438 */         for (graphicInfo = (GraphicInfo) graphicInfoList.iterator(); ((Iterator) graphicInfo).hasNext(); ) { graphicInfo = (GraphicInfo)((Iterator) graphicInfo).next();
/*     */ 
/* 440 */           if (graphicInfo.getX() > theMaxX) {
/* 441 */             theMaxX = graphicInfo.getX();
/*     */           }
/*     */ 
/* 444 */           if (graphicInfo.getX() < theMinX) {
/* 445 */             theMinX = graphicInfo.getX();
/*     */           }
/*     */ 
/* 449 */           if (graphicInfo.getY() > theMaxY) {
/* 450 */             theMaxY = graphicInfo.getY();
/*     */           }
/*     */ 
/* 453 */           if (graphicInfo.getY() < theMinY) {
/* 454 */             theMinY = graphicInfo.getY();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 460 */     int nrOfLanes = 0;
/*     */ 
/* 462 */     for (Process process : bpmnModel.getProcesses()) {
/* 463 */       for (Lane l : process.getLanes()) {
/* 464 */         nrOfLanes++;
/*     */ 
/* 466 */          graphicInfo = bpmnModel.getGraphicInfo(l.getId());
/*     */ 
/* 469 */         if (graphicInfo.getX() + graphicInfo.getWidth() > theMaxX) {
/* 470 */           theMaxX = graphicInfo.getX() + graphicInfo.getWidth();
/*     */         }
/*     */ 
/* 473 */         if (graphicInfo.getX() < theMinX) {
/* 474 */           theMinX = graphicInfo.getX();
/*     */         }
/*     */ 
/* 478 */         if (graphicInfo.getY() + graphicInfo.getHeight() > theMaxY) {
/* 479 */           theMaxY = graphicInfo.getY() + graphicInfo.getHeight();
/*     */         }
/*     */ 
/* 482 */         if (graphicInfo.getY() < theMinY) {
/* 483 */           theMinY = graphicInfo.getY();
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 489 */     if ((flowNodes.size() == 0) && (bpmnModel.getPools().size() == 0) && 
/* 490 */       (nrOfLanes == 0))
/*     */     {
/* 492 */       theMinX = 0.0D;
/* 493 */       theMinY = 0.0D;
/*     */     }
/*     */ 
/* 496 */     return new Point((int)theMinX, (int)theMinY);
/*     */   }
/*     */ 
/*     */   protected static List<Artifact> gatherAllArtifacts(BpmnModel bpmnModel) {
/* 500 */     List artifacts = new ArrayList();
/*     */ 
/* 502 */     for (Process process : bpmnModel.getProcesses()) {
/* 503 */       artifacts.addAll(process.getArtifacts());
/*     */     }
/*     */ 
/* 506 */     return artifacts;
/*     */   }
/*     */ 
/*     */   protected static List<FlowNode> gatherAllFlowNodes(BpmnModel bpmnModel) {
/* 510 */     List flowNodes = new ArrayList();
/*     */ 
/* 512 */     for (Process process : bpmnModel.getProcesses()) {
/* 513 */       flowNodes.addAll(gatherAllFlowNodes(process));
/*     */     }
/*     */ 
/* 516 */     return flowNodes;
/*     */   }
/*     */ 
/*     */   protected static List<FlowNode> gatherAllFlowNodes(FlowElementsContainer flowElementsContainer)
/*     */   {
/* 521 */     List flowNodes = new ArrayList();
/*     */ 
/* 523 */     for (FlowElement flowElement : flowElementsContainer.getFlowElements()) {
/* 524 */       if ((flowElement instanceof FlowNode)) {
/* 525 */         flowNodes.add((FlowNode)flowElement);
/*     */       }
/*     */ 
/* 528 */       if (!(flowElement instanceof FlowElementsContainer)) continue;
/* 529 */       flowNodes
/* 530 */         .addAll(gatherAllFlowNodes((FlowElementsContainer)flowElement));
/*     */     }
/*     */ 
/* 534 */     return flowNodes;
/*     */   }
/*     */ 
/*     */   public void drawHistoryFlow(BufferedImage image, String processInstanceId) {
/* 538 */     HistoricProcessInstance historicProcessInstance = 
/* 539 */       Context.getCommandContext().getHistoricProcessInstanceEntityManager()
/* 540 */       .findHistoricProcessInstance(processInstanceId);
/* 541 */     String processDefinitionId = historicProcessInstance
/* 542 */       .getProcessDefinitionId();
/* 543 */     Graph graph = new ActivitiHistoryGraphBuilder(processInstanceId)
/* 544 */       .build();
/*     */ 
/* 546 */     for (Edge edge : graph.getEdges())
/* 547 */       drawSequenceFlow(image, processDefinitionId, edge.getName());
/*     */   }
/*     */ 
/*     */   public void drawSequenceFlow(BufferedImage image, String processDefinitionId, String sequenceFlowId)
/*     */   {
/* 553 */     GetBpmnModelCmd getBpmnModelCmd = new GetBpmnModelCmd(
/* 554 */       processDefinitionId);
/* 555 */     BpmnModel bpmnModel = getBpmnModelCmd.execute(
/* 556 */       Context.getCommandContext());
/*     */ 
/* 558 */     Graphics2D graphics = image.createGraphics();
/* 559 */     graphics.setPaint(HISTORY_COLOR);
/* 560 */     graphics.setStroke(new BasicStroke(2.0F));
/*     */     try
/*     */     {
/* 563 */       List graphicInfoList = bpmnModel
/* 564 */         .getFlowLocationGraphicInfo(sequenceFlowId);
/*     */ 
/* 566 */       int[] xPoints = new int[graphicInfoList.size()];
/* 567 */       int[] yPoints = new int[graphicInfoList.size()];
/*     */ 
/* 569 */       for (int i = 1; i < graphicInfoList.size(); i++) {
/* 570 */         GraphicInfo graphicInfo = (GraphicInfo)graphicInfoList.get(i);
/* 571 */         GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(i - 1);
/*     */ 
/* 573 */         if (i == 1) {
/* 574 */           xPoints[0] = ((int)previousGraphicInfo.getX() - this.minX);
/* 575 */           yPoints[0] = ((int)previousGraphicInfo.getY() - this.minY);
/*     */         }
/*     */ 
/* 578 */         xPoints[i] = ((int)graphicInfo.getX() - this.minX);
/* 579 */         yPoints[i] = ((int)graphicInfo.getY() - this.minY);
/*     */       }
/*     */ 
/* 582 */       int radius = 15;
/*     */ 
/* 584 */       Path2D path = new Path2D.Double();
/*     */ 
/* 586 */       for (int i = 0; i < xPoints.length; i++) {
/* 587 */         Integer anchorX = Integer.valueOf(xPoints[i]);
/* 588 */         Integer anchorY = Integer.valueOf(yPoints[i]);
/*     */ 
/* 590 */         double targetX = anchorX.intValue();
/* 591 */         double targetY = anchorY.intValue();
/*     */ 
/* 593 */         double ax = 0.0D;
/* 594 */         double ay = 0.0D;
/* 595 */         double bx = 0.0D;
/* 596 */         double by = 0.0D;
/* 597 */         double zx = 0.0D;
/* 598 */         double zy = 0.0D;
/*     */ 
/* 600 */         if ((i > 0) && (i < xPoints.length - 1)) {
/* 601 */           Integer cx = anchorX;
/* 602 */           Integer cy = anchorY;
/*     */ 
/* 605 */           double lineLengthY = yPoints[i] - yPoints[(i - 1)];
/*     */ 
/* 608 */           double lineLengthX = xPoints[i] - xPoints[(i - 1)];
/* 609 */           double lineLength = Math.sqrt(Math.pow(lineLengthY, 2.0D) + 
/* 610 */             Math.pow(lineLengthX, 2.0D));
/* 611 */           double dx = lineLengthX * radius / lineLength;
/* 612 */           double dy = lineLengthY * radius / lineLength;
/* 613 */           targetX -= dx;
/* 614 */           targetY -= dy;
/*     */ 
/* 617 */           if ((lineLength < 2 * radius) && (i > 1)) {
/* 618 */             targetX = xPoints[i] - lineLengthX / 2.0D;
/* 619 */             targetY = yPoints[i] - lineLengthY / 2.0D;
/*     */           }
/*     */ 
/* 623 */           lineLengthY = yPoints[(i + 1)] - yPoints[i];
/* 624 */           lineLengthX = xPoints[(i + 1)] - xPoints[i];
/* 625 */           lineLength = Math.sqrt(Math.pow(lineLengthY, 2.0D) + 
/* 626 */             Math.pow(lineLengthX, 2.0D));
/*     */ 
/* 628 */           if (lineLength < radius) {
/* 629 */             lineLength = radius;
/*     */           }
/*     */ 
/* 632 */           dx = lineLengthX * radius / lineLength;
/* 633 */           dy = lineLengthY * radius / lineLength;
/*     */ 
/* 635 */           double nextSrcX = xPoints[i] + dx;
/* 636 */           double nextSrcY = yPoints[i] + dy;
/*     */ 
/* 638 */           if ((lineLength < 2 * radius) && 
/* 639 */             (i < xPoints.length - 2)) {
/* 640 */             nextSrcX = xPoints[i] + lineLengthX / 2.0D;
/* 641 */             nextSrcY = yPoints[i] + lineLengthY / 2.0D;
/*     */           }
/*     */ 
/* 644 */           double dx0 = (cx.intValue() - targetX) / 3.0D;
/* 645 */           double dy0 = (cy.intValue() - targetY) / 3.0D;
/* 646 */           ax = cx.intValue() - dx0;
/* 647 */           ay = cy.intValue() - dy0;
/*     */ 
/* 649 */           double dx1 = (cx.intValue() - nextSrcX) / 3.0D;
/* 650 */           double dy1 = (cy.intValue() - nextSrcY) / 3.0D;
/* 651 */           bx = cx.intValue() - dx1;
/* 652 */           by = cy.intValue() - dy1;
/*     */ 
/* 654 */           zx = nextSrcX;
/* 655 */           zy = nextSrcY;
/*     */         }
/*     */ 
/* 658 */         if (i == 0)
/* 659 */           path.moveTo(targetX, targetY);
/*     */         else {
/* 661 */           path.lineTo(targetX, targetY);
/*     */         }
/*     */ 
/* 664 */         if ((i <= 0) || (i >= xPoints.length - 1))
/*     */           continue;
/* 666 */         path.curveTo(ax, ay, bx, by, zx, zy);
/*     */       }
/*     */ 
/* 670 */       graphics.draw(path);
/*     */ 
/* 673 */       Line2D.Double line = new Line2D.Double(xPoints[(xPoints.length - 2)], 
/* 674 */         yPoints[(xPoints.length - 2)], xPoints[(xPoints.length - 1)], 
/* 675 */         yPoints[(xPoints.length - 1)]);
/*     */ 
/* 677 */       int ARROW_WIDTH = 5;
/* 678 */       int doubleArrowWidth = 2 * ARROW_WIDTH;
/* 679 */       Polygon arrowHead = new Polygon();
/* 680 */       arrowHead.addPoint(0, 0);
/* 681 */       arrowHead.addPoint(-ARROW_WIDTH, -doubleArrowWidth);
/* 682 */       arrowHead.addPoint(ARROW_WIDTH, -doubleArrowWidth);
/*     */ 
/* 684 */       AffineTransform transformation = new AffineTransform();
/* 685 */       transformation.setToIdentity();
/*     */ 
/* 687 */       double angle = Math.atan2(line.y2 - line.y1, line.x2 - line.x1);
/* 688 */       transformation.translate(line.x2, line.y2);
/* 689 */       transformation.rotate(angle - 1.570796326794897D);
/*     */ 
/* 691 */       AffineTransform originalTransformation = graphics.getTransform();
/* 692 */       graphics.setTransform(transformation);
/* 693 */       graphics.fill(arrowHead);
/* 694 */       graphics.setTransform(originalTransformation);
/*     */     } finally {
/* 696 */       graphics.dispose();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.CustomProcessDiagramGenerator
 * JD-Core Version:    0.6.0
 */