	@Test
	public void individualNamedBeans() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBean("a", BeanA.class);
		context.registerBean("b", BeanB.class);
		context.registerBean("c", BeanC.class);
		context.refresh();

		assertSame(context.getBean("b"), context.getBean("a", BeanA.class).b);
		assertSame(context.getBean("c"), context.getBean("a", BeanA.class).c);
		assertSame(context, context.getBean("b", BeanB.class).applicationContext);
	}
