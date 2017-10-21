	@SuppressWarnings("")
	@Test
	public void testIntroductionInterceptorDoesntReplaceToString() throws Exception {
		TestBean raw = new TestBean();
		assertTrue(! (raw instanceof TimeStamped));
		ProxyFactory factory = new ProxyFactory(raw);

		TimeStamped ts = new SerializableTimeStamped(0);

		factory.addAdvisor(0, new DefaultIntroductionAdvisor(new DelegatingIntroductionInterceptor(ts) {
			@Override
			public String toString() {
				throw new UnsupportedOperationException("Shouldn't be invoked");
			}
		}));

		TimeStamped tsp = (TimeStamped) factory.getProxy();
		assertEquals(0, tsp.getTimeStamp());

		assertEquals(raw.toString(), tsp.toString());
	}
