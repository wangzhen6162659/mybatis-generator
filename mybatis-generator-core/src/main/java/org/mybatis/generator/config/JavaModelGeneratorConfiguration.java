/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.config;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * @author Jeff Butler
 */
public class JavaModelGeneratorConfiguration extends PropertyHolder {

    private String targetPackage;
    
    private String targetProject;
    private String targetSuperClass;

    private String targetSuperGeneric;

    /** Example 的包路径  */
    private String targetExamplePackage;
    /** Example 的包项目路径  */
    private String targetExampleProject;

    /** Example 父类 */
    private String targetExampleSuperClass;
    /** GeneratedCriteria 父类 */
    private String targetGeneratedCriteriaSuperClass;

    /**
     * 
     */
    public JavaModelGeneratorConfiguration() {
        super();
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }

    public String getTargetSuperClass() {
        return targetSuperClass;
    }

    public void setTargetSuperClass(String targetSuperClass) {
        this.targetSuperClass = targetSuperClass;
    }

    public String getTargetSuperGeneric() {
        return targetSuperGeneric;
    }

    public void setTargetSuperGeneric(String targetSuperGeneric) {
        this.targetSuperGeneric = targetSuperGeneric;
    }

    public String getTargetExampleProject() {
		return targetExampleProject;
	}

	public void setTargetExampleProject(String targetExampleProject) {
		this.targetExampleProject = targetExampleProject;
	}

	public String getTargetExamplePackage() {
		return targetExamplePackage;
	}

	public void setTargetExamplePackage(String targetExamplePackage) {
		this.targetExamplePackage = targetExamplePackage;
	}

    public String getTargetExampleSuperClass() {
        return targetExampleSuperClass;
    }

    public void setTargetExampleSuperClass(String targetExampleSuperClass) {
        this.targetExampleSuperClass = targetExampleSuperClass;
    }

    public String getTargetGeneratedCriteriaSuperClass() {
        return targetGeneratedCriteriaSuperClass;
    }

    public void setTargetGeneratedCriteriaSuperClass(String targetGeneratedCriteriaSuperClass) {
        this.targetGeneratedCriteriaSuperClass = targetGeneratedCriteriaSuperClass;
    }

    public XmlElement toXmlElement() {
        XmlElement answer = new XmlElement("javaModelGenerator"); //$NON-NLS-1$

        if (targetPackage != null) {
            answer.addAttribute(new Attribute("targetPackage", targetPackage)); //$NON-NLS-1$
        }

        if (targetProject != null) {
            answer.addAttribute(new Attribute("targetProject", targetProject)); //$NON-NLS-1$
        }
        if (targetSuperClass != null) {
            answer.addAttribute(new Attribute("targetSuperClass", targetSuperClass)); //$NON-NLS-1$
        }
        if (targetSuperGeneric != null) {
            answer.addAttribute(new Attribute("targetSuperGeneric", targetSuperGeneric)); //$NON-NLS-1$
        }

        if (targetExampleProject != null) {
            answer.addAttribute(new Attribute("targetExampleProject", targetExampleProject)); //tangyh
        }
        
        if(targetExamplePackage != null){
        	answer.addAttribute(new Attribute("targetExamplePackage", targetExamplePackage)); //tangyh
        }
        if(targetExampleSuperClass != null){
            answer.addAttribute(new Attribute("targetExampleSuperClass", targetExampleSuperClass)); //tangyh
        }
        if(targetGeneratedCriteriaSuperClass != null){
            answer.addAttribute(new Attribute("targetGeneratedCriteriaSuperClass", targetGeneratedCriteriaSuperClass)); //tangyh
        }

        addPropertyXmlElements(answer);

        return answer;
    }

    public void validate(List<String> errors, String contextId) {
        if (!stringHasValue(targetProject)) {
            errors.add(getString("ValidationError.0", contextId)); //$NON-NLS-1$
        }
        
        if (!stringHasValue(targetPackage)) {
            errors.add(getString("ValidationError.12", //$NON-NLS-1$
                    "JavaModelGenerator", contextId)); //$NON-NLS-1$
        }
    }
}
