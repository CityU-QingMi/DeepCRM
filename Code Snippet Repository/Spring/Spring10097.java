	@Test
	public void doNotTokenizeArrayElements() {
		this.tokenizer = new Jackson2Tokenizer(this.jsonParser, false);

		testTokenize(
				singletonList("{\"foo\": \"foofoo\", \"bar\": \"barbar\"}"),
				singletonList("{\"foo\": \"foofoo\", \"bar\": \"barbar\"}"));

		testTokenize(
				asList("{\"foo\": \"foofoo\"",
						", \"bar\": \"barbar\"}"),
				singletonList("{\"foo\":\"foofoo\",\"bar\":\"barbar\"}"));

		testTokenize(
				singletonList("[{\"foo\": \"foofoo\", \"bar\": \"barbar\"},{\"foo\": \"foofoofoo\", \"bar\": \"barbarbar\"}]"),
				singletonList("[{\"foo\": \"foofoo\", \"bar\": \"barbar\"},{\"foo\": \"foofoofoo\", \"bar\": \"barbarbar\"}]"));

		testTokenize(
				singletonList("[{\"foo\": \"bar\"},{\"foo\": \"baz\"}]"),
				singletonList("[{\"foo\": \"bar\"},{\"foo\": \"baz\"}]"));

		testTokenize(
				asList("[{\"foo\": \"foofoo\", \"bar\"",
						": \"barbar\"},{\"foo\": \"foofoofoo\", \"bar\": \"barbarbar\"}]"),
				singletonList("[{\"foo\": \"foofoo\", \"bar\": \"barbar\"},{\"foo\": \"foofoofoo\", \"bar\": \"barbarbar\"}]"));

		testTokenize(
				asList("[",
						"{\"id\":1,\"name\":\"Robert\"}",
						",",
						"{\"id\":2,\"name\":\"Raide\"}",
						",",
						"{\"id\":3,\"name\":\"Ford\"}",
						"]"),
				singletonList("[{\"id\":1,\"name\":\"Robert\"},{\"id\":2,\"name\":\"Raide\"},{\"id\":3,\"name\":\"Ford\"}]"));
	}
