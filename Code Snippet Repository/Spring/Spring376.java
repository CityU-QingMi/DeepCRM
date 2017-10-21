	@Test
	public void testSuffixAndPrefixAssignment() {
		PerformanceMonitorInterceptor interceptor = new PerformanceMonitorInterceptor();

		assertNotNull(interceptor.getPrefix());
		assertNotNull(interceptor.getSuffix());

		interceptor.setPrefix(null);
		interceptor.setSuffix(null);

		assertNotNull(interceptor.getPrefix());
		assertNotNull(interceptor.getSuffix());
	}
