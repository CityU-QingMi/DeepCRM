	@Test
	public void autowiredFieldResolvesWithBaseQualifierAndNonDefaultValue() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs1 = new ConstructorArgumentValues();
		cavs1.addGenericArgumentValue("the real juergen");
		RootBeanDefinition person1 = new RootBeanDefinition(Person.class, cavs1, null);
		person1.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "juergen"));
		ConstructorArgumentValues cavs2 = new ConstructorArgumentValues();
		cavs2.addGenericArgumentValue("juergen imposter");
		RootBeanDefinition person2 = new RootBeanDefinition(Person.class, cavs2, null);
		person2.addQualifier(new AutowireCandidateQualifier(Qualifier.class, "not really juergen"));
		context.registerBeanDefinition("juergen1", person1);
		context.registerBeanDefinition("juergen2", person2);
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedConstructorArgumentWithBaseQualifierNonDefaultValueTestBean.class));
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		context.refresh();
		QualifiedConstructorArgumentWithBaseQualifierNonDefaultValueTestBean bean =
				(QualifiedConstructorArgumentWithBaseQualifierNonDefaultValueTestBean) context.getBean("autowired");
		assertEquals("the real juergen", bean.getPerson().getName());
	}
