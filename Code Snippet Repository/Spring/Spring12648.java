	@Test
	public void addWebRequestInterceptors() throws Exception {
		this.registry.addWebRequestInterceptor(this.webInterceptor1);
		this.registry.addWebRequestInterceptor(this.webInterceptor2);
		List<HandlerInterceptor> interceptors = getInterceptorsForPath(null);

		assertEquals(2, interceptors.size());
		verifyWebInterceptor(interceptors.get(0), this.webInterceptor1);
		verifyWebInterceptor(interceptors.get(1), this.webInterceptor2);
	}
