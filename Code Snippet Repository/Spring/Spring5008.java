	@Test
	public void generifiedClass() throws Exception {
		Class<?> clazz = GenerifiedClass.class;

		Constructor<?> ctor = clazz.getDeclaredConstructor(Object.class);
		String[] names = discoverer.getParameterNames(ctor);
		assertEquals(1, names.length);
		assertEquals("key", names[0]);

		ctor = clazz.getDeclaredConstructor(Object.class, Object.class);
		names = discoverer.getParameterNames(ctor);
		assertEquals(2, names.length);
		assertEquals("key", names[0]);
		assertEquals("value", names[1]);

		Method m = clazz.getMethod("generifiedStaticMethod", Object.class);
		names = discoverer.getParameterNames(m);
		assertEquals(1, names.length);
		assertEquals("param", names[0]);

		m = clazz.getMethod("generifiedMethod", Object.class, long.class, Object.class, Object.class);
		names = discoverer.getParameterNames(m);
		assertEquals(4, names.length);
		assertEquals("param", names[0]);
		assertEquals("x", names[1]);
		assertEquals("key", names[2]);
		assertEquals("value", names[3]);

		m = clazz.getMethod("voidStaticMethod", Object.class, long.class, int.class);
		names = discoverer.getParameterNames(m);
		assertEquals(3, names.length);
		assertEquals("obj", names[0]);
		assertEquals("x", names[1]);
		assertEquals("i", names[2]);

		m = clazz.getMethod("nonVoidStaticMethod", Object.class, long.class, int.class);
		names = discoverer.getParameterNames(m);
		assertEquals(3, names.length);
		assertEquals("obj", names[0]);
		assertEquals("x", names[1]);
		assertEquals("i", names[2]);

		m = clazz.getMethod("getDate");
		names = discoverer.getParameterNames(m);
		assertEquals(0, names.length);
	}
