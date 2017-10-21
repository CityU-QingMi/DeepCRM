	@Test
	public void testGetObjectType() throws Exception {
		TestClass1 tc1 = new TestClass1();
		MethodInvokingFactoryBean mcfb = new MethodInvokingFactoryBean();
		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetObject(tc1);
		mcfb.setTargetMethod("method1");
		mcfb.afterPropertiesSet();
		assertTrue(int.class.equals(mcfb.getObjectType()));

		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("voidRetvalMethod");
		mcfb.afterPropertiesSet();
		Class<?> objType = mcfb.getObjectType();
		assertSame(objType, void.class);

		// verify that we can call a method with args that are subtypes of the
		// target method arg types
		TestClass1._staticField1 = 0;
		mcfb = new MethodInvokingFactoryBean();
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes");
		mcfb.setArguments(new ArrayList<>(), new ArrayList<Object>(), "hello");
		mcfb.afterPropertiesSet();
		mcfb.getObjectType();

		// fail on improper argument types at afterPropertiesSet
		mcfb = new MethodInvokingFactoryBean();
		mcfb.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		mcfb.setTargetClass(TestClass1.class);
		mcfb.setTargetMethod("supertypes");
		mcfb.setArguments("1", new Object());
		try {
			mcfb.afterPropertiesSet();
			fail("Should have thrown NoSuchMethodException");
		}
		catch (NoSuchMethodException ex) {
			// expected
		}
	}
