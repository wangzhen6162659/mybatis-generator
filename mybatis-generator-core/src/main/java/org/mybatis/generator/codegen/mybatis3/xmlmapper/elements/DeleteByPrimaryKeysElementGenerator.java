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

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author tyh
 * @createTime 2017-12-12 11:11
 */
public class DeleteByPrimaryKeysElementGenerator extends
        AbstractXmlElementGenerator {

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("delete"); //<delete />

        answer.addAttribute(new Attribute(
                "id", "deleteByIds")); //id="deleteByPrimaryKeys"
        String parameterClass = "list";
        answer.addAttribute(new Attribute("parameterType", // parameterType="map"
                parameterClass));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("delete from  ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        sb.append(" where id in \n" +
                "    <foreach collection=\"list\" open=\"(\" close=\")\" index=\"index\" item=\"item\" separator=\",\">\n" +
                "      #{item}\n" +
                "    </foreach> "); //$NON-NLS-1$
        answer.addElement(new TextElement(sb.toString()));

        if (context.getPlugins()
                .sqlMapDeleteByPrimaryKeyElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
