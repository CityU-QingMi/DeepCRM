	@Test
	public void testDisposableBean() {
		DefaultSingletonBeanRegistry beanRegistry = new DefaultSingletonBeanRegistry();

		DerivedTestBean tb = new DerivedTestBean();
		beanRegistry.registerSingleton("tb", tb);
		beanRegistry.registerDisposableBean("tb", tb);
		assertSame(tb, beanRegistry.getSingleton("tb"));

		assertSame(tb, beanRegistry.getSingleton("tb"));
		assertEquals(1, beanRegistry.getSingletonCount());
		String[] names = beanRegistry.getSingletonNames();
		assertEquals(1, names.length);
		assertEquals("tb", names[0]);
		assertFalse(tb.wasDestroyed());

		beanRegistry.destroySingletons();
		assertEquals(0, beanRegistry.getSingletonCount());
		assertEquals(0, beanRegistry.getSingletonNames().length);
		assertTrue(tb.wasDestroyed());
	}
