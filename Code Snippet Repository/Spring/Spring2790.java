	@Test
	public void testAdviceImplementsIntroductionInfo() throws Throwable {
		TestBean tb = new TestBean();
		String name = "tony";
		tb.setName(name);
		ProxyFactory pc = new ProxyFactory(tb);
		NopInterceptor di = new NopInterceptor();
		pc.addAdvice(di);
		final long ts = 37;
		pc.addAdvice(new DelegatingIntroductionInterceptor(new TimeStamped() {
			@Override
			public long getTimeStamp() {
				return ts;
			}
		}));

		ITestBean proxied = (ITestBean) createProxy(pc);
		assertEquals(name, proxied.getName());
		TimeStamped intro = (TimeStamped) proxied;
		assertEquals(ts, intro.getTimeStamp());
	}
