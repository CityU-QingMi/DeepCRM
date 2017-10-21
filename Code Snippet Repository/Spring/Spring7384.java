	@Test
	public void canConvertFromStrictContentTypeMatch() {
		this.converter = new TestMessageConverter(Arrays.asList(MimeTypeUtils.TEXT_PLAIN));
		this.converter.setStrictContentTypeMatch(true);

		Message<String> message = MessageBuilder.withPayload("ABC").build();
		assertFalse(this.converter.canConvertFrom(message, String.class));

		message = MessageBuilder.withPayload("ABC")
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN).build();
		assertTrue(this.converter.canConvertFrom(message, String.class));

	}
