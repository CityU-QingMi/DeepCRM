	@Test
	public void individualBeans() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(BeanA.class, BeanB.class, BeanC.class);
		context.refresh();

		assertSame(context.getBean(BeanB.class), context.getBean(BeanA.class).b);
		assertSame(context.getBean(BeanC.class), context.getBean(BeanA.class).c);
		assertSame(context, context.getBean(BeanB.class).applicationContext);
	}
