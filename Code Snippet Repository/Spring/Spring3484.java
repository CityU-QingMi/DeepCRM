	@Test
	public void testRegisteredBeanFactoryPostProcessor() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("tb1", TestBean.class);
		ac.registerSingleton("tb2", TestBean.class);
		TestBeanFactoryPostProcessor bfpp = new TestBeanFactoryPostProcessor();
		ac.addBeanFactoryPostProcessor(bfpp);
		assertFalse(bfpp.wasCalled);
		ac.refresh();
		assertTrue(bfpp.wasCalled);
	}
