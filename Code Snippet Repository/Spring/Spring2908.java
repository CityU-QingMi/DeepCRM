	@Test
	public void testAutowiredConstructorArgumentResolvesQualifiedCandidate() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs1 = new ConstructorArgumentValues();
		cavs1.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person1 = new RootBeanDefinition(Person.class, cavs1, null);
		person1.addQualifier(new AutowireCandidateQualifier(TestQualifier.class));
		ConstructorArgumentValues cavs2 = new ConstructorArgumentValues();
		cavs2.addGenericArgumentValue(MARK);
		RootBeanDefinition person2 = new RootBeanDefinition(Person.class, cavs2, null);
		context.registerBeanDefinition(JUERGEN, person1);
		context.registerBeanDefinition(MARK, person2);
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedConstructorArgumentTestBean.class));
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		context.refresh();
		QualifiedConstructorArgumentTestBean bean =
				(QualifiedConstructorArgumentTestBean) context.getBean("autowired");
		assertEquals(JUERGEN, bean.getPerson().getName());
	}
