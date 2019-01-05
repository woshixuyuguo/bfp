/*    */ package org.jeecgframework.workflow.model.diagram;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import org.activiti.engine.impl.interceptor.Command;
/*    */ import org.activiti.engine.impl.interceptor.CommandContext;
/*    */ 
/*    */ public class HistoryProcessInstanceDiagramCmd
/*    */   implements Command<InputStream>
/*    */ {
/*    */   protected String historyProcessInstanceId;
/*    */ 
/*    */   public HistoryProcessInstanceDiagramCmd(String historyProcessInstanceId)
/*    */   {
/* 12 */     this.historyProcessInstanceId = historyProcessInstanceId;
/*    */   }
/*    */ 
/*    */   public InputStream execute(CommandContext commandContext) {
/*    */     try {
/* 17 */       CustomProcessDiagramGenerator customProcessDiagramGenerator = new CustomProcessDiagramGenerator();
/* 18 */       return customProcessDiagramGenerator.generateDiagram(this.historyProcessInstanceId); } catch (Exception ex) {
/*    */     }
String ex = null;
/* 20 */     throw new RuntimeException(ex);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.model.diagram.HistoryProcessInstanceDiagramCmd
 * JD-Core Version:    0.6.0
 */