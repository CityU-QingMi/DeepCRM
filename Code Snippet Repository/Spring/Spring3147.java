	@Test
	public void individualBeanWithSupplier() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBean(BeanA.class,
				() -> new BeanA(context.getBean(BeanB.class), context.getBean(BeanC.class)));
		context.registerBean(BeanB.class, BeanB::new);
		context.registerBean(BeanC.class, BeanC::new);
		context.refresh();

		assertTrue(context.getBeanFactory().containsSingleton("annotationConfigApplicationContextTests.BeanA"));
		assertSame(context.getBean(BeanB.class), context.getBean(BeanA.class).b);
		assertSame(context.getBean(BeanC.class), context.getBean(BeanA.class).c);
		assertSame(context, context.getBean(BeanB.class).applicationContext);

		assertArrayEquals(new String[] {"annotationConfigApplicationContextTests.BeanA"},
				context.getDefaultListableBeanFactory().getDependentBeans("annotationConfigApplicationContextTests.BeanB"));
		assertArrayEquals(new String[] {"annotationConfigApplicationContextTests.BeanA"},
				context.getDefaultListableBeanFactory().getDependentBeans("annotationConfigApplicationContextTests.BeanC"));
	}
