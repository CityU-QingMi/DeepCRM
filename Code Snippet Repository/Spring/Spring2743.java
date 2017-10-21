	@Test
	public void testRetryAspect() throws Exception {
		ClassPathXmlApplicationContext bf = newContext("retryAspect.xml");
		UnreliableBean bean = (UnreliableBean) bf.getBean("unreliableBean");
		RetryAspect aspect = (RetryAspect) bf.getBean("retryAspect");
		int attempts = bean.unreliable();
		assertEquals(2, attempts);
		assertEquals(2, aspect.getBeginCalls());
		assertEquals(1, aspect.getRollbackCalls());
		assertEquals(1, aspect.getCommitCalls());
	}
