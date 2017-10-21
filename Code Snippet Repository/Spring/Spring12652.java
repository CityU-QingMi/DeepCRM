	@Test
	public void nonOrderedInterceptors() throws Exception {
		this.registry.addInterceptor(this.interceptor1).order(0);
		this.registry.addInterceptor(this.interceptor2).order(0);

		List<Object> interceptors = this.registry.getInterceptors();
		assertEquals(2, interceptors.size());

		assertSame(this.interceptor1, interceptors.get(0));
		assertSame(this.interceptor2, interceptors.get(1));
	}
