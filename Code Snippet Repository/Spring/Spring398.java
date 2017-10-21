	@Test
	public void testAutomaticInterfaceRecognitionInSubclass() throws Exception {
		final long t = 1001L;
		@SuppressWarnings("serial")
		class TestII extends DelegatingIntroductionInterceptor implements TimeStamped, ITester {
			@Override
			public void foo() throws Exception {
			}
			@Override
			public long getTimeStamp() {
				return t;
			}
		}

		DelegatingIntroductionInterceptor ii = new TestII();

		TestBean target = new TestBean();

		ProxyFactory pf = new ProxyFactory(target);
		IntroductionAdvisor ia = new DefaultIntroductionAdvisor(ii);
		assertTrue(ia.isPerInstance());
		pf.addAdvisor(0, ia);

		//assertTrue(Arrays.binarySearch(pf.getProxiedInterfaces(), TimeStamped.class) != -1);
		TimeStamped ts = (TimeStamped) pf.getProxy();

		assertThat(ts, instanceOf(TimeStamped.class));
		// Shoulnd't proxy framework interfaces
		assertTrue(!(ts instanceof MethodInterceptor));
		assertTrue(!(ts instanceof IntroductionInterceptor));

		assertTrue(ts.getTimeStamp() == t);
		((ITester) ts).foo();
		((ITestBean) ts).getAge();

		// Test removal
		ii.suppressInterface(TimeStamped.class);
		// Note that we need to construct a new proxy factory,
		// or suppress the interface on the proxy factory
		pf = new ProxyFactory(target);
		pf.addAdvisor(0, new DefaultIntroductionAdvisor(ii));
		Object o = pf.getProxy();
		assertTrue(!(o instanceof TimeStamped));
	}
