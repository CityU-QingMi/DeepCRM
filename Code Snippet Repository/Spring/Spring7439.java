	@Test
	public void resolveOptionalHeaderAsEmpty() throws Exception {
		GenericApplicationContext cxt = new GenericApplicationContext();
		cxt.refresh();

		HeaderMethodArgumentResolver resolver =
				new HeaderMethodArgumentResolver(new DefaultConversionService(), cxt.getBeanFactory());

		Message<String> message = MessageBuilder.withPayload("foo").build();
		Object result = resolver.resolveArgument(paramOptional, message);
		assertEquals(Optional.empty(), result);
	}
