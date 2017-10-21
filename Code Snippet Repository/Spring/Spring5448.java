	@Test
	public void plainMethodInvoker() throws Exception {
		// sanity check: singleton, non-static should work
		TestClass1 tc1 = new TestClass1();
		MethodInvoker mi = new MethodInvoker();
		mi.setTargetObject(tc1);
		mi.setTargetMethod("method1");
		mi.prepare();
		Integer i = (Integer) mi.invoke();
		assertEquals(1, i.intValue());

		// sanity check: check that argument count matching works
		mi = new MethodInvoker();
		mi.setTargetClass(TestClass1.class);
		mi.setTargetMethod("supertypes");
		mi.setArguments(new Object[] {new ArrayList<>(), new ArrayList<>(), "hello"});
		mi.prepare();
		assertEquals("hello", mi.invoke());

		mi = new MethodInvoker();
		mi.setTargetClass(TestClass1.class);
		mi.setTargetMethod("supertypes2");
		mi.setArguments(new Object[] {new ArrayList<>(), new ArrayList<>(), "hello", "bogus"});
		mi.prepare();
		assertEquals("hello", mi.invoke());

		// Sanity check: check that argument conversion doesn't work with plain MethodInvoker
		mi = new MethodInvoker();
		mi.setTargetClass(TestClass1.class);
		mi.setTargetMethod("supertypes2");
		mi.setArguments(new Object[] {new ArrayList<>(), new ArrayList<>(), "hello", Boolean.TRUE});

		exception.expect(NoSuchMethodException.class);
		mi.prepare();
	}
