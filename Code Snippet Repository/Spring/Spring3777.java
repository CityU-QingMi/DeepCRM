	@Test
	public void testLookupWithProxyInterfaceWithNotCache() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		final TestBean tb = new TestBean();
		jof.setJndiTemplate(new JndiTemplate() {
			@Override
			public Object lookup(String name) {
				if ("foo".equals(name)) {
					tb.setName("tb");
					tb.setAge(tb.getAge() + 1);
					return tb;
				}
				return null;
			}
		});
		jof.setJndiName("foo");
		jof.setProxyInterface(ITestBean.class);
		jof.setCache(false);
		jof.afterPropertiesSet();
		assertTrue(jof.getObject() instanceof ITestBean);
		ITestBean proxy = (ITestBean) jof.getObject();
		assertEquals("tb", tb.getName());
		assertEquals(1, tb.getAge());
		proxy.returnsThis();
		assertEquals(2, tb.getAge());
		proxy.haveBirthday();
		assertEquals(4, tb.getAge());
	}
