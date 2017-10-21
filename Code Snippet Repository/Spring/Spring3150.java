	@Test
	public void individualNamedBeanWithSupplierAndCustomizer() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerBean("a", BeanA.class,
				() -> new BeanA(context.getBean(BeanB.class), context.getBean(BeanC.class)),
				bd -> bd.setLazyInit(true));
		context.registerBean("b", BeanB.class, BeanB::new);
		context.registerBean("c", BeanC.class, BeanC::new);
		context.refresh();

		assertFalse(context.getBeanFactory().containsSingleton("a"));
		assertSame(context.getBean("b", BeanB.class), context.getBean(BeanA.class).b);
		assertSame(context.getBean("c"), context.getBean("a", BeanA.class).c);
		assertSame(context, context.getBean("b", BeanB.class).applicationContext);
	}
