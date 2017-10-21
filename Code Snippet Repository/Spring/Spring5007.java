	@Test
	public void overloadedMethodInInnerClass() throws Exception {
		Class<InnerClass> clazz = InnerClass.class;

		Method m1 = clazz.getMethod("instanceMethod", new Class[] { String.class });
		String[] names = discoverer.getParameterNames(m1);
		assertNotNull("should find method info", names);
		assertEquals("one argument", 1, names.length);
		assertEquals("aa", names[0]);

		Method m2 = clazz.getMethod("instanceMethod", new Class[] { String.class, String.class });
		names = discoverer.getParameterNames(m2);
		assertNotNull("should find method info", names);
		assertEquals("two arguments", 2, names.length);
		assertEquals("aa", names[0]);
		assertEquals("bb", names[1]);
	}
