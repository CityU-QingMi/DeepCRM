	@Test
	public void overloadedStaticMethod() throws Exception {
		Class<? extends LocalVariableTableParameterNameDiscovererTests> clazz = this.getClass();

		Method m1 = clazz.getMethod("staticMethod", new Class[] { Long.TYPE, Long.TYPE });
		String[] names = discoverer.getParameterNames(m1);
		assertNotNull("should find method info", names);
		assertEquals("two arguments", 2, names.length);
		assertEquals("x", names[0]);
		assertEquals("y", names[1]);

		Method m2 = clazz.getMethod("staticMethod", new Class[] { Long.TYPE, Long.TYPE, Long.TYPE });
		names = discoverer.getParameterNames(m2);
		assertNotNull("should find method info", names);
		assertEquals("three arguments", 3, names.length);
		assertEquals("x", names[0]);
		assertEquals("y", names[1]);
		assertEquals("z", names[2]);
	}
