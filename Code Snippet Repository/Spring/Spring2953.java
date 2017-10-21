	@Test
	public void testQualifiedByAttributesWithCustomQualifierRegistered() {
		StaticApplicationContext context = new StaticApplicationContext();
		BeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(CONFIG_LOCATION);
		QualifierAnnotationAutowireCandidateResolver resolver = (QualifierAnnotationAutowireCandidateResolver)
				context.getDefaultListableBeanFactory().getAutowireCandidateResolver();
		resolver.addQualifierType(MultipleAttributeQualifier.class);
		context.registerSingleton("testBean", MultiQualifierClient.class);
		context.refresh();

		MultiQualifierClient testBean = (MultiQualifierClient) context.getBean("testBean");

		assertNotNull( testBean.factoryTheta);
		assertNotNull( testBean.implTheta);
	}
