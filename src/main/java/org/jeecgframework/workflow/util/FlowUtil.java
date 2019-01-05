/*    */ package org.jeecgframework.workflow.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FlowUtil
/*    */ {
/*    */   public List stringToList(String content)
/*    */   {
/* 22 */     String[] s = content.split(",");
/* 23 */     return Arrays.asList(s);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\jeecg-bpm-core-MF-1.0.0.jar
 * Qualified Name:     org.jeecgframework.workflow.util.FlowUtil
 * JD-Core Version:    0.6.0
 */