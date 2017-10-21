	@Test
	public void resolveOptionalHeaderWithValue() throws Exception {
		GenericApplicationContext cxt = new GenericApplicationContext();
		cxt.refresh();

		HeaderMethodArgumentResolver resolver =
				new HeaderMethodArgumentResolver(new DefaultConversionService(), cxt.getBeanFactory());

		Message<String> message = MessageBuilder.withPayload("foo").setHeader("foo", "bar").build();
		Object result = resolver.resolveArgument(paramOptional, message);
		assertEquals(Optional.of("bar"), result);
	}
