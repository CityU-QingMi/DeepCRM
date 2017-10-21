	@Test
	public void testSingletons() {
		DefaultSingletonBeanRegistry beanRegistry = new DefaultSingletonBeanRegistry();

		TestBean tb = new TestBean();
		beanRegistry.registerSingleton("tb", tb);
		assertSame(tb, beanRegistry.getSingleton("tb"));

		TestBean tb2 = (TestBean) beanRegistry.getSingleton("tb2", new ObjectFactory<Object>() {
			@Override
			public Object getObject() throws BeansException {
				return new TestBean();
			}
		});
		assertSame(tb2, beanRegistry.getSingleton("tb2"));

		assertSame(tb, beanRegistry.getSingleton("tb"));
		assertSame(tb2, beanRegistry.getSingleton("tb2"));
		assertEquals(2, beanRegistry.getSingletonCount());
		String[] names = beanRegistry.getSingletonNames();
		assertEquals(2, names.length);
		assertEquals("tb", names[0]);
		assertEquals("tb2", names[1]);

		beanRegistry.destroySingletons();
		assertEquals(0, beanRegistry.getSingletonCount());
		assertEquals(0, beanRegistry.getSingletonNames().length);
	}
