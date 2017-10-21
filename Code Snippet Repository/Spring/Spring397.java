	@Test
	public void testAutomaticInterfaceRecognitionInDelegate() throws Exception {
		final long t = 1001L;
		class Tester implements TimeStamped, ITester {
			@Override
			public void foo() throws Exception {
			}
			@Override
			public long getTimeStamp() {
				return t;
			}
		}

		DelegatingIntroductionInterceptor ii = new DelegatingIntroductionInterceptor(new Tester());

		TestBean target = new TestBean();

		ProxyFactory pf = new ProxyFactory(target);
		pf.addAdvisor(0, new DefaultIntroductionAdvisor(ii));

		//assertTrue(Arrays.binarySearch(pf.getProxiedInterfaces(), TimeStamped.class) != -1);
		TimeStamped ts = (TimeStamped) pf.getProxy();

		assertTrue(ts.getTimeStamp() == t);
		((ITester) ts).foo();

		((ITestBean) ts).getAge();
	}
