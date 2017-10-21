	@Test
	public void testManyProxies() {
		Assume.group(TestGroup.PERFORMANCE);
		int howMany = 10000;
		StopWatch sw = new StopWatch();
		sw.start("Create " + howMany + " proxies");
		testManyProxies(howMany);
		sw.stop();
		assertTrue("Proxy creation was too slow",  sw.getTotalTimeMillis() < 5000);
	}
