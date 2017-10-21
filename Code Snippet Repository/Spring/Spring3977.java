	@Test
	public void testCanPassInMoreThanOneProperty() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("groovy-multiple-properties.xml", getClass());
		TestBean tb = (TestBean) ctx.getBean("testBean");

		ContextScriptBean bean = (ContextScriptBean) ctx.getBean("bean");
		assertEquals("The first property ain't bein' injected.", "Sophie Marceau", bean.getName());
		assertEquals("The second property ain't bein' injected.", 31, bean.getAge());
		assertEquals(tb, bean.getTestBean());
		assertEquals(ctx, bean.getApplicationContext());

		ContextScriptBean bean2 = (ContextScriptBean) ctx.getBean("bean2");
		assertEquals(tb, bean2.getTestBean());
		assertEquals(ctx, bean2.getApplicationContext());
	}
