	@Test
	public void testIntroductionInterceptorWithSuperInterface() throws Exception {
		TestBean raw = new TestBean();
		assertTrue(! (raw instanceof TimeStamped));
		ProxyFactory factory = new ProxyFactory(raw);

		TimeStamped ts = mock(SubTimeStamped.class);
		long timestamp = 111L;
		given(ts.getTimeStamp()).willReturn(timestamp);

		factory.addAdvisor(0, new DefaultIntroductionAdvisor(new DelegatingIntroductionInterceptor(ts), TimeStamped.class));

		TimeStamped tsp = (TimeStamped) factory.getProxy();
		assertTrue(!(tsp instanceof SubTimeStamped));
		assertTrue(tsp.getTimeStamp() == timestamp);
	}
