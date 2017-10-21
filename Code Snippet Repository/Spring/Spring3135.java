	@Test
	public void individualBeanWithSpecifiedConstructorArguments() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		BeanB b = new BeanB();
		BeanC c = new BeanC();
		context.registerBean(BeanA.class, b, c);
		context.refresh();

		assertSame(b, context.getBean(BeanA.class).b);
		assertSame(c, context.getBean(BeanA.class).c);
		assertNull(b.applicationContext);
	}
