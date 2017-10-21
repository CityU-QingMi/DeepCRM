	@Test
	public void overloadedStaticMethodInInnerClass() throws Exception {
		Class<InnerClass> clazz = InnerClass.class;

		Method m1 = clazz.getMethod("staticMethod", new Class[] { Long.TYPE });
		String[] names = discoverer.getParameterNames(m1);
		assertNotNull("should find method info", names);
		assertEquals("one argument", 1, names.length);
		assertEquals("x", names[0]);

		Method m2 = clazz.getMethod("staticMethod", new Class[] { Long.TYPE, Long.TYPE });
		names = discoverer.getParameterNames(m2);
		assertNotNull("should find method info", names);
		assertEquals("two arguments", 2, names.length);
		assertEquals("x", names[0]);
		assertEquals("y", names[1]);
	}
