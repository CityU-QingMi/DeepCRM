	@Test
	public void testForName() throws ClassNotFoundException {
		assertEquals(String.class, ClassUtils.forName("java.lang.String", classLoader));
		assertEquals(String[].class, ClassUtils.forName("java.lang.String[]", classLoader));
		assertEquals(String[].class, ClassUtils.forName(String[].class.getName(), classLoader));
		assertEquals(String[][].class, ClassUtils.forName(String[][].class.getName(), classLoader));
		assertEquals(String[][][].class, ClassUtils.forName(String[][][].class.getName(), classLoader));
		assertEquals(TestObject.class, ClassUtils.forName("org.springframework.tests.sample.objects.TestObject", classLoader));
		assertEquals(TestObject[].class, ClassUtils.forName("org.springframework.tests.sample.objects.TestObject[]", classLoader));
		assertEquals(TestObject[].class, ClassUtils.forName(TestObject[].class.getName(), classLoader));
		assertEquals(TestObject[][].class, ClassUtils.forName("org.springframework.tests.sample.objects.TestObject[][]", classLoader));
		assertEquals(TestObject[][].class, ClassUtils.forName(TestObject[][].class.getName(), classLoader));
		assertEquals(short[][][].class, ClassUtils.forName("[[[S", classLoader));
	}
