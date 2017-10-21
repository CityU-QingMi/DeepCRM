	@Test
	public void individualNamedBeanWithMixedConstructorArguments() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		BeanC c = new BeanC();
		context.registerBean("a", BeanA.class, c);
		context.registerBean("b", BeanB.class);
		context.refresh();

		assertSame(context.getBean("b", BeanB.class), context.getBean("a", BeanA.class).b);
		assertSame(c, context.getBean("a", BeanA.class).c);
		assertSame(context, context.getBean("b", BeanB.class).applicationContext);
	}
