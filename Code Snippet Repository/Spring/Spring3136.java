	@Test
	public void individualNamedBeanWithSpecifiedConstructorArguments() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		BeanB b = new BeanB();
		BeanC c = new BeanC();
		context.registerBean("a", BeanA.class, b, c);
		context.refresh();

		assertSame(b, context.getBean("a", BeanA.class).b);
		assertSame(c, context.getBean("a", BeanA.class).c);
		assertNull(b.applicationContext);
	}
