	@Before
	public void setup() throws Exception {
		this.server.setHandler(new CheckRequestHandler());
		this.server.afterPropertiesSet();
		this.server.start();

		// Set dynamically chosen port
		this.port = this.server.getPort();

		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
				builder.build(), NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
				socketFactory).build();
		HttpComponentsClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpclient);
		this.restTemplate = new RestTemplate(requestFactory);
	}
