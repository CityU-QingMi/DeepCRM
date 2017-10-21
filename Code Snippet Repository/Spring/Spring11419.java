	@Test
	public void ofResourceRange() throws Exception {
		final int rangeStart = 10;
		Resource body = new ClassPathResource("response.txt", getClass());
		BodyInserter<Resource, ReactiveHttpOutputMessage> inserter = BodyInserters.fromResource(body);

		MockServerHttpRequest request = MockServerHttpRequest.get("/foo")
				.range(HttpRange.createByteRange(rangeStart))
				.build();
		MockServerHttpResponse response = new MockServerHttpResponse();
		Mono<Void> result = inserter.insert(response, new BodyInserter.Context() {
			@Override
			public List<HttpMessageWriter<?>> messageWriters() {
				return Collections.singletonList(new ResourceHttpMessageWriter());
			}

			@Override
			public Optional<ServerHttpRequest> serverRequest() {
				return Optional.of(request);
			}

			@Override
			public Map<String, Object> hints() {
				return hints;
			}
		});
		StepVerifier.create(result).expectComplete().verify();

		byte[] allBytes = Files.readAllBytes(body.getFile().toPath());
		byte[] expectedBytes = new byte[allBytes.length - rangeStart];
		System.arraycopy(allBytes, rangeStart, expectedBytes, 0, expectedBytes.length);

		StepVerifier.create(response.getBody())
				.consumeNextWith(dataBuffer -> {
					byte[] resultBytes = new byte[dataBuffer.readableByteCount()];
					dataBuffer.read(resultBytes);
					assertArrayEquals(expectedBytes, resultBytes);
				})
				.expectComplete()
				.verify();
	}
