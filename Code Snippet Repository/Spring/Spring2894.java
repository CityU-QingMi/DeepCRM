	@Test
	public void testScopedOverride() throws Exception {
		GenericApplicationContext ctx = new GenericApplicationContext();
		new XmlBeanDefinitionReader(ctx).loadBeanDefinitions(OVERRIDE_CONTEXT);
		SimpleMapScope scope = new SimpleMapScope();
		ctx.getBeanFactory().registerScope("request", scope);
		ctx.refresh();

		ITestBean bean = (ITestBean) ctx.getBean("testBean");
		assertEquals("male", bean.getName());
		assertEquals(99, bean.getAge());

		assertTrue(scope.getMap().containsKey("scopedTarget.testBean"));
		assertEquals(TestBean.class, scope.getMap().get("scopedTarget.testBean").getClass());
	}
