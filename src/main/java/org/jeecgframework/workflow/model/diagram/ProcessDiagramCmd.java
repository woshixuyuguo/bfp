/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import org.activiti.engine.impl.interceptor.Command;
/*    */ import org.activiti.engine.impl.interceptor.CommandContext;
/*    */ 
/*    */ public class ProcessDiagramCmd
/*    */   implements Command<InputStream>
/*    */ {
/*    */   protected String deploymentId;
/*    */ 
/*    */   public ProcessDiagramCmd(String deploymentId)
/*    */   {
/* 13 */     this.deploymentId = deploymentId;
/*    */   }
/*    */ 
/*    */   public InputStream execute(CommandContext commandContext) {
/*    */     try {
/* 18 */       CustomProcessDiagramGenerator customProcessDiagramGenerator = new CustomProcessDiagramGenerator();
/* 19 */       return customProcessDiagramGenerator.generateDiagram(this.deploymentId); } catch (Exception ex) {
/*    */     }
String ex = null;
/* 21 */     throw new RuntimeException(ex);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.ProcessDiagramCmd
 * JD-Core Version:    0.6.0
 */