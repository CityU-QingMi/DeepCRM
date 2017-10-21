	@Test
	public void testDynamicTargetSource() throws NoSuchMethodException {
		// Install facade
		CallCountingTransactionManager txMan = new CallCountingTransactionManager();
		PlatformTransactionManagerFacade.delegate = txMan;

		TestBean tb = (TestBean) factory.getBean("hotSwapped");
		assertEquals(666, tb.getAge());
		int newAge = 557;
		tb.setAge(newAge);
		assertEquals(newAge, tb.getAge());

		TestBean target2 = new TestBean();
		target2.setAge(65);
		HotSwappableTargetSource ts = (HotSwappableTargetSource) factory.getBean("swapper");
		ts.swap(target2);
		assertEquals(target2.getAge(), tb.getAge());
		tb.setAge(newAge);
		assertEquals(newAge, target2.getAge());

		assertEquals(0, txMan.inflight);
		assertEquals(2, txMan.commits);
		assertEquals(0, txMan.rollbacks);
	}
