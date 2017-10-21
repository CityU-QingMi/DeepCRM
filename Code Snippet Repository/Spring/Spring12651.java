	@Test
	public void orderedInterceptors() throws Exception {
		this.registry.addInterceptor(this.interceptor1).order(Ordered.LOWEST_PRECEDENCE);
		this.registry.addInterceptor(this.interceptor2).order(Ordered.HIGHEST_PRECEDENCE);

		List<Object> interceptors = this.registry.getInterceptors();
		assertEquals(2, interceptors.size());

		assertSame(this.interceptor2, interceptors.get(0));
		assertSame(this.interceptor1, interceptors.get(1));
	}
