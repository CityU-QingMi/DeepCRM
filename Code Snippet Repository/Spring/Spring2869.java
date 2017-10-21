	private Object testPrototypeInstancesAreIndependent(String beanName) {
		// Initial count value set in bean factory XML
		int INITIAL_COUNT = 10;

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource(PROTOTYPE_CONTEXT, CLASS));

		// Check it works without AOP
		SideEffectBean raw = (SideEffectBean) bf.getBean("prototypeTarget");
		assertEquals(INITIAL_COUNT, raw.getCount() );
		raw.doWork();
		assertEquals(INITIAL_COUNT+1, raw.getCount() );
		raw = (SideEffectBean) bf.getBean("prototypeTarget");
		assertEquals(INITIAL_COUNT, raw.getCount() );

		// Now try with advised instances
		SideEffectBean prototype2FirstInstance = (SideEffectBean) bf.getBean(beanName);
		assertEquals(INITIAL_COUNT, prototype2FirstInstance.getCount() );
		prototype2FirstInstance.doWork();
		assertEquals(INITIAL_COUNT + 1, prototype2FirstInstance.getCount() );

		SideEffectBean prototype2SecondInstance = (SideEffectBean) bf.getBean(beanName);
		assertFalse("Prototypes are not ==", prototype2FirstInstance == prototype2SecondInstance);
		assertEquals(INITIAL_COUNT, prototype2SecondInstance.getCount() );
		assertEquals(INITIAL_COUNT + 1, prototype2FirstInstance.getCount() );

		return prototype2FirstInstance;
	}
