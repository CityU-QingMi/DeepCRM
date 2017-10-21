	@Test
	public void writePojoWithPrettyPrint() {

		ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().indentOutput(true).build();
		this.messageWriter = new ServerSentEventHttpMessageWriter(new Jackson2JsonEncoder(mapper));

		Flux<Pojo> source = Flux.just(new Pojo("foofoo", "barbar"), new Pojo("foofoofoo", "barbarbar"));
		MockServerHttpResponse outputMessage = new MockServerHttpResponse();
		testWrite(source, outputMessage, Pojo.class);

		StepVerifier.create(outputMessage.getBodyAsString())
				.expectNext("data:{\n" +
						"data:  \"foo\" : \"foofoo\",\n" +
						"data:  \"bar\" : \"barbar\"\n" + "data:}\n\n" +
						"data:{\n" +
						"data:  \"foo\" : \"foofoofoo\",\n" +
						"data:  \"bar\" : \"barbarbar\"\n" + "data:}\n\n")
				.expectComplete()
				.verify();
	}
