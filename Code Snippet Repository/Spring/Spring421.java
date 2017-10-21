	@Test
	public void testCreateLazy() {
		TargetSource targetSource = new AbstractLazyCreationTargetSource() {
			@Override
			protected Object createObject() {
				return new InitCountingBean();
			}
			@Override
			public Class<?> getTargetClass() {
				return InitCountingBean.class;
			}
		};

		InitCountingBean proxy = (InitCountingBean) ProxyFactory.getProxy(targetSource);
		assertEquals("Init count should be 0", 0, InitCountingBean.initCount);
		assertEquals("Target class incorrect", InitCountingBean.class, targetSource.getTargetClass());
		assertEquals("Init count should still be 0 after getTargetClass()", 0, InitCountingBean.initCount);

		proxy.doSomething();
		assertEquals("Init count should now be 1", 1, InitCountingBean.initCount);

		proxy.doSomething();
		assertEquals("Init count should still be 1", 1, InitCountingBean.initCount);
	}
