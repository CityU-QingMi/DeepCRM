	@Test
	public void individualBeanWithMixedConstructorArguments() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		BeanC c = new BeanC();
		context.registerBean(BeanA.class, c);
		context.registerBean(BeanB.class);
		context.refresh();

		assertSame(context.getBean(BeanB.class), context.getBean(BeanA.class).b);
		assertSame(c, context.getBean(BeanA.class).c);
		assertSame(context, context.getBean(BeanB.class).applicationContext);
	}
