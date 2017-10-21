	@Test
	public void captureTheRest() {
		checkMatches("/resource/{*foobar}", "/resource");
		checkNoMatch("/resource/{*foobar}", "/resourceX");
		checkNoMatch("/resource/{*foobar}", "/resourceX/foobar");
		checkMatches("/resource/{*foobar}", "/resource/foobar");
		checkCapture("/resource/{*foobar}", "/resource/foobar", "foobar", "/foobar");
		checkCapture("/customer/{*something}", "/customer/99", "something", "/99");
		checkCapture("/customer/{*something}", "/customer/aa/bb/cc", "something",
				"/aa/bb/cc");
		checkCapture("/customer/{*something}", "/customer/", "something", "/");
		checkCapture("/customer/////{*something}", "/customer/////", "something", "/");
		checkCapture("/customer/////{*something}", "/customer//////", "something", "//");
		checkCapture("/customer//////{*something}", "/customer//////99", "something", "/99");
		checkCapture("/customer//////{*something}", "/customer//////99", "something", "/99");
		checkCapture("/customer/{*something}", "/customer", "something", "");
		checkCapture("/{*something}", "", "something", "");
		checkCapture("/customer/{*something}", "/customer//////99", "something", "//////99");
	}
