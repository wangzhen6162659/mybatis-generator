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
package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

public class BatchInsertMethodGenerator extends
		AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		Method method = new Method();

		//不设置返回类型：void
		method.setReturnType(new FullyQualifiedJavaType("void"));
		
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("batchInsert");

		FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType listType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        importedTypes.add(listType);
        parameterType.addTypeArgument(listType);
        
		importedTypes.add(parameterType);
		method.addParameter(new Parameter(parameterType, "list")); //$NON-NLS-1$

		context.getCommentGenerator().addGeneralMethodComment(method,
				introspectedTable);

		addMapperAnnotations(interfaze, method);

		if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
				introspectedTable)) {
			interfaze.addImportedTypes(importedTypes);
			interfaze.addMethod(method);
		}

	}

	public void addMapperAnnotations(Interface interfaze, Method method) {
		return;
	}

}
