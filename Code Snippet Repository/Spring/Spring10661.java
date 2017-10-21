	@Test
	@SuppressWarnings("")
	public void registerSingleClass() {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(Config.class);
		ctx.refresh();

		TestBean bean = ctx.getBean(TestBean.class);
		assertNotNull(bean);
	}
