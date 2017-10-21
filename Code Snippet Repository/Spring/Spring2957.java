	@Test
	public void testQualifiedByFieldName() {
		StaticApplicationContext context = new StaticApplicationContext();
		BeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(CONFIG_LOCATION);
		context.registerSingleton("testBean", QualifiedByFieldNameTestBean.class);
		context.refresh();
		QualifiedByFieldNameTestBean testBean = (QualifiedByFieldNameTestBean) context.getBean("testBean");
		Person person = testBean.getLarry();
		assertEquals("LarryBean", person.getName());
	}
