	@Test
	public void testIntroductionMasksTargetImplementation() throws Exception {
		final long t = 1001L;
		@SuppressWarnings("serial")
		class TestII extends DelegatingIntroductionInterceptor implements TimeStamped {
			@Override
			public long getTimeStamp() {
				return t;
			}
		}

		DelegatingIntroductionInterceptor ii = new TestII();

		// != t
		TestBean target = new TargetClass(t + 1);

		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvisor(0, new DefaultIntroductionAdvisor(ii));

		TimeStamped ts = (TimeStamped) pf.getProxy();
		// From introduction interceptor, not target
		assertTrue(ts.getTimeStamp() == t);
	}
