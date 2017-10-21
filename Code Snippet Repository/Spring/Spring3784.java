	@Test
	public void testLookupWithExposeAccessContext() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		TestBean tb = new TestBean();
		final Context mockCtx = mock(Context.class);
		given(mockCtx.lookup("foo")).willReturn(tb);
		jof.setJndiTemplate(new JndiTemplate() {
			@Override
			protected Context createInitialContext() {
				return mockCtx;
			}
		});
		jof.setJndiName("foo");
		jof.setProxyInterface(ITestBean.class);
		jof.setExposeAccessContext(true);
		jof.afterPropertiesSet();
		assertTrue(jof.getObject() instanceof ITestBean);
		ITestBean proxy = (ITestBean) jof.getObject();
		assertEquals(0, tb.getAge());
		proxy.setAge(99);
		assertEquals(99, tb.getAge());
		proxy.equals(proxy);
		proxy.hashCode();
		proxy.toString();
		verify(mockCtx, times(2)).close();
	}
