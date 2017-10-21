	@SuppressWarnings("")
	@Parameters
	public static Iterable<? extends ClientHttpRequestFactory> data() {
		return Arrays.asList(
				new SimpleClientHttpRequestFactory(),
				new HttpComponentsClientHttpRequestFactory(),
				new Netty4ClientHttpRequestFactory(),
				new OkHttp3ClientHttpRequestFactory()
		);
	}
