	@Before
	public void setup() throws Exception {
		HttpMessageConverter<Object> converter = new MappingJackson2HttpMessageConverter();
		this.errorHandler = new ExtractingResponseErrorHandler(
				Collections.singletonList(converter));

		this.errorHandler.setStatusMapping(
				Collections.singletonMap(HttpStatus.I_AM_A_TEAPOT, MyRestClientException.class));
		this.errorHandler.setSeriesMapping(Collections
				.singletonMap(HttpStatus.Series.SERVER_ERROR, MyRestClientException.class));
	}
