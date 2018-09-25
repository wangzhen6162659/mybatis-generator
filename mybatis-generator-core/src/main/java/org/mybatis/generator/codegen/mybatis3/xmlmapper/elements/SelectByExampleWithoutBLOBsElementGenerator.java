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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectByExampleWithoutBLOBsElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectByExampleWithoutBLOBsElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String fqjt = introspectedTable.getExampleType();

        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                introspectedTable.getSelectByExampleStatementId()));
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select")); //$NON-NLS-1$
        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "distinct")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
        answer.addElement(ifElement);

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable
                .getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,"); //$NON-NLS-1$
            answer.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(getBaseColumnListElement());

        sb.setLength(0);
        sb.append("from "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement((new TextElement(sb.toString())));
        answer.addElement(getExampleIncludeElement());
        
        //group by 
        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "groupByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("group by ${groupByClause}")); //$NON-NLS-1$
        answer.addElement(ifElement);
        
        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "orderByClause != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("order by ${orderByClause}")); //$NON-NLS-1$
        answer.addElement(ifElement);
        
        //limit
        ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "limitValue != null")); //$NON-NLS-1$ //$NON-NLS-2$
        ifElement.addElement(new TextElement("limit ${limitValue}")); //$NON-NLS-1$
        answer.addElement(ifElement);
        
        
        //tangyunhan---------------增加分页added by oxj  生成对应的xml配置分页的节点--------------------------- 
//        XmlElement foreachElement = new XmlElement("foreach"); 
//        foreachElement.addAttribute(new Attribute("collection", "oredCriteria")); 
//        foreachElement.addAttribute(new Attribute("item","criteria")); 
//        
//        ifElement = new XmlElement("if"); 
//        ifElement.addAttribute(new Attribute("test","criteria.valid")); 
//        foreachElement.addElement(ifElement); 
//        
//        XmlElement foreach2Element = new XmlElement("foreach"); 
//        foreach2Element.addAttribute(new Attribute("collection", "criteria.criteria")); 
//        foreach2Element.addAttribute(new Attribute("item","criterion")); 
//        ifElement.addElement(foreach2Element); 
//        
//        XmlElement if2Element = new XmlElement("if"); 
//        if2Element.addAttribute(new Attribute("test","criterion.limitValue")); 
//        foreach2Element.addElement(if2Element); 
//        
//        if2Element.addElement(new TextElement("limit ${criterion.value},${criterion.secondValue}")); 
//        answer.addElement(ifElement); 
        /**  以上创建的分页节点结构如下 
         *   <foreach collection="oredCriteria" item="criteria"> 
         *	<if test="criteria.valid"> 
         *	<foreach collection="criteria.criteria" item="criterion"> 
         *	<if test= "criterion.limitValue"> 
         *	limit ${criterion.value},${criterion.secondValue} 
         *	</if> 
         *		</foreach> 
      	 *	</if> 
      	 *	</foreach> 
         */ 
        
        if (context.getPlugins()
                .sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
