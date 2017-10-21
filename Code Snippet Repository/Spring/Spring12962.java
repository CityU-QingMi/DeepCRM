	@Before
	public void setup() {
		ByteArrayHttpMessageConverter emptyBodyConverter = new ByteArrayHttpMessageConverter();
		emptyBodyConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

		List<HttpMessageConverter<?>> converters = new ArrayList<>(3);
		converters.add(emptyBodyConverter);
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new MappingJackson2HttpMessageConverter());

		AllEncompassingFormHttpMessageConverter converter = new AllEncompassingFormHttpMessageConverter();
		converter.setPartConverters(converters);

		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		restTemplate.setMessageConverters(Collections.singletonList(converter));
	}
