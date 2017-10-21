	@Test
	public void testCanGetFactoryReferenceAndManipulate() {
		ProxyFactoryBean config = (ProxyFactoryBean) factory.getBean("&test1");
		assertTrue("Has correct object type", ITestBean.class.isAssignableFrom(config.getObjectType()));
		assertTrue("Has correct object type", ITestBean.class.isAssignableFrom(factory.getType("test1")));
		// Trigger lazy initialization.
		config.getObject();
		assertEquals("Have one advisors", 1, config.getAdvisors().length);
		assertTrue("Has correct object type", ITestBean.class.isAssignableFrom(config.getObjectType()));
		assertTrue("Has correct object type", ITestBean.class.isAssignableFrom(factory.getType("test1")));

		ITestBean tb = (ITestBean) factory.getBean("test1");
		// no exception
		tb.hashCode();

		final Exception ex = new UnsupportedOperationException("invoke");
		// Add evil interceptor to head of list
		config.addAdvice(0, new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				throw ex;
			}
		});
		assertEquals("Have correct advisor count", 2, config.getAdvisors().length);

		tb = (ITestBean) factory.getBean("test1");
		try {
			// Will fail now
			tb.toString();
			fail("Evil interceptor added programmatically should fail all method calls");
		}
		catch (Exception thrown) {
			assertTrue(thrown == ex);
		}
	}
