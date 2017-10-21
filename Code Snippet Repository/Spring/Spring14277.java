	@Test
	public void testSingletonScoping() throws Exception {
		ITestBean scoped = (ITestBean) this.context.getBean("singletonScoped");
		assertTrue("Should be AOP proxy", AopUtils.isAopProxy(scoped));
		assertTrue("Should be target class proxy", scoped instanceof TestBean);
		String rob = "Rob Harrop";
		String bram = "Bram Smeets";
		assertEquals(rob, scoped.getName());
		scoped.setName(bram);
		assertEquals(bram, scoped.getName());
		ITestBean deserialized = (ITestBean) SerializationTestUtils.serializeAndDeserialize(scoped);
		assertEquals(bram, deserialized.getName());
	}
