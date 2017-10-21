	@Test
	public void testIntroductionInterceptorWithInterfaceHierarchy() throws Exception {
		TestBean raw = new TestBean();
		assertTrue(! (raw instanceof SubTimeStamped));
		ProxyFactory factory = new ProxyFactory(raw);

		TimeStamped ts = mock(SubTimeStamped.class);
		long timestamp = 111L;
		given(ts.getTimeStamp()).willReturn(timestamp);

		factory.addAdvisor(0, new DefaultIntroductionAdvisor(new DelegatingIntroductionInterceptor(ts), SubTimeStamped.class));

		SubTimeStamped tsp = (SubTimeStamped) factory.getProxy();
		assertTrue(tsp.getTimeStamp() == timestamp);
	}
