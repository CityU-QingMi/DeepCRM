	@Test
	public void spr11124MultipleAnnotations() throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Spr11124Config.class);
		Spr11124Service bean = context.getBean(Spr11124Service.class);
		bean.single(2);
		bean.single(2);
		bean.multiple(2);
		bean.multiple(2);
		context.close();
	}
