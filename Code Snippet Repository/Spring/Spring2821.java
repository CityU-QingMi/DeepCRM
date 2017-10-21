	@Test
	public void testSerializationAdviceNotSerializable() throws Exception {
		SerializablePerson sp = new SerializablePerson();
		assertTrue(SerializationTestUtils.isSerializable(sp));

		ProxyFactory pf = new ProxyFactory(sp);

		// This isn't serializable
		Advice i = new NopInterceptor();
		pf.addAdvice(i);
		assertFalse(SerializationTestUtils.isSerializable(i));
		Object proxy = createAopProxy(pf).getProxy();

		assertFalse(SerializationTestUtils.isSerializable(proxy));
	}
