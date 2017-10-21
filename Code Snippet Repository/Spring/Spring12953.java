	@Test
	public void jsonpResponseBodyAdvice() throws Exception {

		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		this.handlerAdapter.setMessageConverters(converters);

		this.webAppContext.registerSingleton("jsonpAdvice", JsonpAdvice.class);
		this.webAppContext.refresh();

		testJsonp("callback", true);
		testJsonp("_callback", true);
		testJsonp("_Call.bAcK", true);
		testJsonp("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.", true);

		testJsonp("<script>", false);
		testJsonp("!foo!bar", false);
	}
