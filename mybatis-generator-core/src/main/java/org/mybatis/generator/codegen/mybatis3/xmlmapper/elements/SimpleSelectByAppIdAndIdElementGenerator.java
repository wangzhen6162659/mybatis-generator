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

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * @author tyh
 * @createTime 2017-12-12 10:42
 */
public class SimpleSelectByAppIdAndIdElementGenerator extends
        AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        IntrospectedColumn appId = introspectedTable.getColumn("app_id");
        IntrospectedColumn id = introspectedTable.getColumn("id");
        if(appId == null || id == null){
            progressCallback.startTask(
                    introspectedTable.getFullyQualifiedTable().toString() + " not app_id or id column"
            );
            return;
        }

        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                "selectByAppIdAndId"));
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", "map")); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        answer.addElement(new TextElement("select"));

        StringBuilder sb = new StringBuilder();
        answer.addElement(getBaseColumnListElement());  //<include refid="Base_Column_List" />

        sb.setLength(0);
        sb.append("from ");
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement((new TextElement(sb.toString())));   //from TABLE

        sb.setLength(0);
        sb.append("where 1=1");
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities.getAliasedEscapedColumnName(appId));
        sb.append(" = "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities.getParameterClause(appId)); // and app_id = #{}
        sb.append(" and ");
        sb.append(MyBatis3FormattingUtilities
                .getAliasedEscapedColumnName(id));
        sb.append(" = "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities.getParameterClause(id)); //  and id = #{}
        answer.addElement(new TextElement(sb.toString()));

        if (context.getPlugins()
                .sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
