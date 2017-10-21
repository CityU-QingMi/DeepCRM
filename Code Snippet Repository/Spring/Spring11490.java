	@Test
	public void render() throws Exception {
		Map<String, Object> model = Collections.singletonMap("foo", "bar");
		Mono<RenderingResponse> result = RenderingResponse.create("view").modelAttributes(model).build();

		MockServerHttpRequest build = MockServerHttpRequest.get("http://localhost").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(build);
		ViewResolver viewResolver = mock(ViewResolver.class);
		View view = mock(View.class);
		when(viewResolver.resolveViewName("view", Locale.ENGLISH)).thenReturn(Mono.just(view));
		when(view.render(model, null, exchange)).thenReturn(Mono.empty());

		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(viewResolver);

		HandlerStrategies mockConfig = mock(HandlerStrategies.class);
		when(mockConfig.viewResolvers()).thenReturn(viewResolvers);

		StepVerifier.create(result)
				.expectNextMatches(response -> "view".equals(response.name()) &&
						model.equals(response.model()))
				.expectComplete()
				.verify();
	}
