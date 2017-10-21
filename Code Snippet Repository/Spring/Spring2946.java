	@Test
	public void autowiredMethodParameterWithStaticallyQualifiedCandidate() {
		GenericApplicationContext context = new GenericApplicationContext();
		ConstructorArgumentValues cavs = new ConstructorArgumentValues();
		cavs.addGenericArgumentValue(JUERGEN);
		RootBeanDefinition person = new RootBeanDefinition(QualifiedPerson.class, cavs, null);
		context.registerBeanDefinition(JUERGEN,
				ScopedProxyUtils.createScopedProxy(new BeanDefinitionHolder(person, JUERGEN), context, true).getBeanDefinition());
		context.registerBeanDefinition("autowired",
				new RootBeanDefinition(QualifiedMethodParameterTestBean.class));
		AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
		context.refresh();
		QualifiedMethodParameterTestBean bean =
				(QualifiedMethodParameterTestBean) context.getBean("autowired");
		assertEquals(JUERGEN, bean.getPerson().getName());
	}
