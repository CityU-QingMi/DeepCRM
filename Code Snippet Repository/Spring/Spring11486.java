	@Test
	public void bodyInserter() throws Exception {
		String body = "foo";
		Publisher<String> publisher = Mono.just(body);
		BiFunction<ServerHttpResponse, BodyInserter.Context, Mono<Void>> writer =
				(response, strategies) -> {
					byte[] bodyBytes = body.getBytes(UTF_8);
					ByteBuffer byteBuffer = ByteBuffer.wrap(bodyBytes);
					DataBuffer buffer = new DefaultDataBufferFactory().wrap(byteBuffer);

					return response.writeWith(Mono.just(buffer));
				};

		Mono<EntityResponse<Publisher<String>>> result = EntityResponse.fromPublisher(publisher, String.class).build();

		MockServerHttpRequest request = MockServerHttpRequest.get("http://localhost").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		ServerResponse.Context context = new ServerResponse.Context() {
			@Override
			public List<HttpMessageWriter<?>> messageWriters() {
				return Collections.singletonList(new EncoderHttpMessageWriter<>(CharSequenceEncoder.allMimeTypes()));
			}

			@Override
			public List<ViewResolver> viewResolvers() {
				return Collections.emptyList();
			}
		};
		StepVerifier.create(result)
				.consumeNextWith(response -> {
					StepVerifier.create(response.entity())
							.expectNext(body)
							.expectComplete()
							.verify();
					response.writeTo(exchange, context);
				})
				.expectComplete()
				.verify();

		assertNotNull(exchange.getResponse().getBody());
	}
