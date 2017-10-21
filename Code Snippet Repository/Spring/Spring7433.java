	@Test
	public void resolveArgument() throws Exception {

		Map<String, Object> vars = new HashMap<>();
		vars.put("foo", "bar");
		vars.put("name", "value");

		Message<byte[]> message = MessageBuilder.withPayload(new byte[0]).setHeader(
			DestinationVariableMethodArgumentResolver.DESTINATION_TEMPLATE_VARIABLES_HEADER, vars).build();

		Object result = this.resolver.resolveArgument(this.paramAnnotated, message);
		assertEquals("bar", result);

		result = this.resolver.resolveArgument(this.paramAnnotatedValue, message);
		assertEquals("value", result);
	}
