	@Test
	public void testCglibTransactionProxyImplementsNoInterfaces() throws NoSuchMethodException {
		ImplementsNoInterfaces ini = (ImplementsNoInterfaces) factory.getBean("cglibNoInterfaces");
		assertTrue("testBean is CGLIB advised", AopUtils.isCglibProxy(ini));
		assertTrue(ini instanceof TransactionalProxy);
		String newName = "Gordon";

		// Install facade
		CallCountingTransactionManager ptm = new CallCountingTransactionManager();
		PlatformTransactionManagerFacade.delegate = ptm;

		ini.setName(newName);
		assertEquals(newName, ini.getName());
		assertEquals(2, ptm.commits);
	}
