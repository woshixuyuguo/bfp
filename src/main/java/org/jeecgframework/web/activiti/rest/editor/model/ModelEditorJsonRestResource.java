/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jeecgframework.web.activiti.rest.editor.model;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author Tijs Rademakers
 */
@RestController
public class ModelEditorJsonRestResource implements ModelDataJsonConstants {
  
  protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);
  
  @Autowired
  private RepositoryService repositoryService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @RequestMapping(value="/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json")
  public ObjectNode getEditorJson(@PathVariable String modelId) {
    ObjectNode modelNode = null;
    
    Model model = repositoryService.getModel(modelId);
    System.err.println(model);
    
    
      
    if (model != null) {
      try {
        if (StringUtils.isNotEmpty(model.getMetaInfo())) {
          modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
        } else {
          modelNode = objectMapper.createObjectNode();
          modelNode.put(MODEL_NAME, model.getName());
        }
        modelNode.put(MODEL_ID, model.getId());
        ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(
            new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
        modelNode.put("model", editorJsonNode);
        
      } catch (Exception e) {
        LOGGER.error("Error creating model JSON", e);
        throw new ActivitiException("Error creating model JSON", e);
      }
    }
    return modelNode;
  }
  
  @RequestMapping(value="/ttt")
  public void ttt(HttpServletRequest request) throws UnsupportedEncodingException{
	  System.out.println("进来了");
	  String descp="测试一下";  
      ObjectMapper objectMapper = new ObjectMapper();  
      ObjectNode editorNode = objectMapper.createObjectNode();  
      editorNode.put("id", "canvas");  
      editorNode.put("resourceId", "canvas");  
      ObjectNode stencilSetNode = objectMapper.createObjectNode();  
      stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
      editorNode.set("stencilset", stencilSetNode);  
      Model modelData = repositoryService.newModel();  
        
      ObjectNode modelObjectNode = objectMapper.createObjectNode();  
      modelObjectNode.put(MODEL_NAME, "123");  
      modelObjectNode.put(MODEL_REVISION, 1);  
      //String description = null;  
       
      modelObjectNode.put(MODEL_DESCRIPTION, descp);  
      modelData.setMetaInfo(modelObjectNode.toString());  
      modelData.setName("123");  
        
      repositoryService.saveModel(modelData);  
      repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));  
  }
}
