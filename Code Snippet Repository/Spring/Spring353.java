	@Test
	public void testAddRepeatedInterface() {
		TimeStamped tst = new TimeStamped() {
			@Override
			public long getTimeStamp() {
				throw new UnsupportedOperationException("getTimeStamp");
			}
		};
		ProxyFactory pf = new ProxyFactory(tst);
		// We've already implicitly added this interface.
		// This call should be ignored without error
		pf.addInterface(TimeStamped.class);
		// All cool
		assertThat(pf.getProxy(), instanceOf(TimeStamped.class));
	}
