	@Test
	public void withScopedProxyThroughAspectJPattern() throws IOException, ClassNotFoundException {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ComponentScanWithScopedProxyThroughAspectJPattern.class);
		ctx.getBeanFactory().registerScope("myScope", new SimpleMapScope());
		ctx.refresh();
		// should cast to the interface
		FooService bean = (FooService) ctx.getBean("scopedProxyTestBean");
		// should be dynamic proxy
		assertThat(AopUtils.isJdkDynamicProxy(bean), is(true));
	}
