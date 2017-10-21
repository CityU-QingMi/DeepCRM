	@Test
	public void classLevelJsonView() throws Exception {
		Flux<DataBuffer> source = Flux.just(stringBuffer(
				"{\"withView1\" : \"with\", \"withView2\" : \"with\", \"withoutView\" : \"without\"}"));
		ResolvableType elementType = forClass(JacksonViewBean.class);
		Map<String, Object> hints = singletonMap(JSON_VIEW_HINT, MyJacksonView3.class);
		Flux<JacksonViewBean> flux = new Jackson2JsonDecoder()
				.decode(source, elementType, null, hints).cast(JacksonViewBean.class);

		StepVerifier.create(flux)
				.consumeNextWith(b -> {
					assertNull(b.getWithView1());
					assertNull(b.getWithView2());
					assertTrue(b.getWithoutView().equals("without"));
				})
				.verifyComplete();
	}
