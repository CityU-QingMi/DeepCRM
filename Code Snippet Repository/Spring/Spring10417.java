	@Test
	public void testGetLongParameterWithDefaultValueHandlingIsFastEnough() {
		Assume.group(TestGroup.PERFORMANCE);
		MockHttpServletRequest request = new MockHttpServletRequest();
		StopWatch sw = new StopWatch();
		sw.start();
		for (int i = 0; i < 1000000; i++) {
			ServletRequestUtils.getLongParameter(request, "nonExistingParam", 0);
		}
		sw.stop();
		System.out.println(sw.getTotalTimeMillis());
		assertTrue("getStringParameter took too long: " + sw.getTotalTimeMillis(), sw.getTotalTimeMillis() < 250);
	}
