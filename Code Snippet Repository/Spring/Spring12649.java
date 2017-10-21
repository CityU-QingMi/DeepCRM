	@Test
	public void addWebRequestInterceptorsWithUrlPatterns() throws Exception {
		this.registry.addWebRequestInterceptor(this.webInterceptor1).addPathPatterns("/path1");
		this.registry.addWebRequestInterceptor(this.webInterceptor2).addPathPatterns("/path2");

		List<HandlerInterceptor> interceptors = getInterceptorsForPath("/path1");
		assertEquals(1, interceptors.size());
		verifyWebInterceptor(interceptors.get(0), this.webInterceptor1);

		interceptors = getInterceptorsForPath("/path2");
		assertEquals(1, interceptors.size());
		verifyWebInterceptor(interceptors.get(0), this.webInterceptor2);
	}
