	@Test
	public void getInterceptors() throws Exception {
		TestInterceptingHttpAccessor accessor = new TestInterceptingHttpAccessor();
		List<ClientHttpRequestInterceptor> interceptors = Arrays.asList(
				new SecondClientHttpRequestInterceptor(),
				new ThirdClientHttpRequestInterceptor(),
				new FirstClientHttpRequestInterceptor()

		);
		accessor.setInterceptors(interceptors);

		assertThat(accessor.getInterceptors().get(0), Matchers.instanceOf(FirstClientHttpRequestInterceptor.class));
		assertThat(accessor.getInterceptors().get(1), Matchers.instanceOf(SecondClientHttpRequestInterceptor.class));
		assertThat(accessor.getInterceptors().get(2), Matchers.instanceOf(ThirdClientHttpRequestInterceptor.class));
	}
