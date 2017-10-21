	@Test
	public void overloadedMethod() throws Exception {
		Class<? extends LocalVariableTableParameterNameDiscovererTests> clazz = this.getClass();

		Method m1 = clazz.getMethod("instanceMethod", new Class[] { Double.TYPE, Double.TYPE });
		String[] names = discoverer.getParameterNames(m1);
		assertNotNull("should find method info", names);
		assertEquals("two arguments", 2, names.length);
		assertEquals("x", names[0]);
		assertEquals("y", names[1]);

		Method m2 = clazz.getMethod("instanceMethod", new Class[] { Double.TYPE, Double.TYPE, Double.TYPE });
		names = discoverer.getParameterNames(m2);
		assertNotNull("should find method info", names);
		assertEquals("three arguments", 3, names.length);
		assertEquals("x", names[0]);
		assertEquals("y", names[1]);
		assertEquals("z", names[2]);
	}
