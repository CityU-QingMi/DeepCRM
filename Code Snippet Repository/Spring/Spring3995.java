	private static BeanDefinition createScriptedGroovyBean() {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(GroovyScriptFactory.class);
		builder.addConstructorArgValue("inline:package org.springframework.scripting;\n" +
				"class GroovyMessenger implements Messenger {\n" +
				"  private String message = \"Bingo\"\n" +
				"  public String getMessage() {\n" +
				"    return this.message\n" +
				"  }\n" +
				"  public void setMessage(String message) {\n" +
				"    this.message = message\n" +
				"  }\n" +
				"}");
		builder.addPropertyValue("message", MESSAGE_TEXT);
		return builder.getBeanDefinition();
	}
