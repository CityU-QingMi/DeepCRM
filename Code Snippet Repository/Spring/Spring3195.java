	@Test
	public void testSingletonScopedFactoryMethod() {
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);

		context.getBeanFactory().registerScope("request", new SimpleMapScope());

		scanner.scan(BASE_PACKAGE);
		context.registerBeanDefinition("clientBean", new RootBeanDefinition(QualifiedClientBean.class));
		context.refresh();

		FactoryMethodComponent fmc = context.getBean("factoryMethodComponent", FactoryMethodComponent.class);
		assertFalse(fmc.getClass().getName().contains(ClassUtils.CGLIB_CLASS_SEPARATOR));

		TestBean tb = (TestBean) context.getBean("publicInstance"); //2
		assertEquals("publicInstance", tb.getName());
		TestBean tb2 = (TestBean) context.getBean("publicInstance"); //2
		assertEquals("publicInstance", tb2.getName());
		assertSame(tb2, tb);

		tb = (TestBean) context.getBean("protectedInstance"); //3
		assertEquals("protectedInstance", tb.getName());
		assertSame(tb, context.getBean("protectedInstance"));
		assertEquals("0", tb.getCountry());
		tb2 = context.getBean("protectedInstance", TestBean.class); //3
		assertEquals("protectedInstance", tb2.getName());
		assertSame(tb2, tb);

		tb = context.getBean("privateInstance", TestBean.class); //4
		assertEquals("privateInstance", tb.getName());
		assertEquals(1, tb.getAge());
		tb2 = context.getBean("privateInstance", TestBean.class); //4
		assertEquals(2, tb2.getAge());
		assertNotSame(tb2, tb);

		Object bean = context.getBean("requestScopedInstance"); //5
		assertTrue(AopUtils.isCglibProxy(bean));
		assertTrue(bean instanceof ScopedObject);

		QualifiedClientBean clientBean = context.getBean("clientBean", QualifiedClientBean.class);
		assertSame(context.getBean("publicInstance"), clientBean.testBean);
		assertSame(context.getBean("dependencyBean"), clientBean.dependencyBean);
		assertSame(context, clientBean.applicationContext);
	}
