	@Test
	public void classLevelJsonView() throws Exception {
		JacksonViewBean bean = new JacksonViewBean();
		bean.setWithView1("with");
		bean.setWithView2("with");
		bean.setWithoutView("without");

		ResolvableType type = ResolvableType.forClass(JacksonViewBean.class);
		Map<String, Object> hints = singletonMap(JSON_VIEW_HINT, MyJacksonView3.class);
		Flux<DataBuffer> output = this.encoder.encode(Mono.just(bean), this.bufferFactory, type, null, hints);

		StepVerifier.create(output)
				.consumeNextWith(stringConsumer("{\"withoutView\":\"without\"}"))
				.verifyComplete();
	}
