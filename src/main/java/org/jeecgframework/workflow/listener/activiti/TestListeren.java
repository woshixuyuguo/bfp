/*    */ package org.jeecgframework.workflow.listener.activiti;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import org.activiti.engine.delegate.DelegateTask;
/*    */ import org.activiti.engine.delegate.TaskListener;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class TestListeren
/*    */   implements TaskListener
/*    */ {
/*    */   public void notify(DelegateTask delegateTask)
/*    */   {
/* 23 */     System.out.print("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD" + delegateTask.getEventName());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.listener.activiti.TestListeren
 * JD-Core Version:    0.6.0
 */