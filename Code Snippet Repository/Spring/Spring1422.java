	@Test
	public void testArgumentConversion() throws Exception {
		MethodInvokingFactoryBean mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes");
		mcfb.setArguments(new ArrayList<>(), new ArrayList<Object>(), "hello", "bogus");
		try {
			mcfb.afterPropertiesSet();
			fail("Matched method with wrong number of args");
		}
		catch (NoSuchMethodException ex) {
			// expected
		}

		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes");
		mcfb.setArguments(1, new Object());
		try {
			mcfb.afterPropertiesSet();
			mcfb.getObject();
			fail("Should have failed on getObject with mismatched argument types");
		}
		catch (NoSuchMethodException ex) {
			// expected
		}

		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes2");
		mcfb.setArguments(new ArrayList<>(), new ArrayList<Object>(), "hello", "bogus");
		mcfb.afterPropertiesSet();
		assertEquals("hello", mcfb.getObject());

		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes2");
		mcfb.setArguments(new ArrayList<>(), new ArrayList<Object>(), new Object());
		try {
			mcfb.afterPropertiesSet();
			fail("Matched method when shouldn't have matched");
		}
		catch (NoSuchMethodException ex) {
			// expected
		}
	}
