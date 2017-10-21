	@Test
	public void testQualifiedByParentValue() {
		StaticApplicationContext parent = new StaticApplicationContext();
		GenericBeanDefinition parentLarry = new GenericBeanDefinition();
		parentLarry.setBeanClass(Person.class);
		parentLarry.getPropertyValues().add("name", "ParentLarry");
		parentLarry.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "parentLarry"));
		parent.registerBeanDefinition("someLarry", parentLarry);
		GenericBeanDefinition otherLarry = new GenericBeanDefinition();
		otherLarry.setBeanClass(Person.class);
		otherLarry.getPropertyValues().add("name", "OtherLarry");
		otherLarry.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "otherLarry"));
		parent.registerBeanDefinition("someOtherLarry", otherLarry);
		parent.refresh();

		StaticApplicationContext context = new StaticApplicationContext(parent);
		BeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions(CONFIG_LOCATION);
		context.registerSingleton("testBean", QualifiedByParentValueTestBean.class);
		context.refresh();
		QualifiedByParentValueTestBean testBean = (QualifiedByParentValueTestBean) context.getBean("testBean");
		Person person = testBean.getLarry();
		assertEquals("ParentLarry", person.getName());
	}
