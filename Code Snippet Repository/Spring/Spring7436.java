	@Test
	public void resolveDefaultValueSystemProperty() throws Exception {
		System.setProperty("systemProperty", "sysbar");
		try {
			Message<byte[]> message = MessageBuilder.withPayload(new byte[0]).build();
			Object result = resolver.resolveArgument(paramSystemPropertyDefaultValue, message);
			assertEquals("sysbar", result);
		}
		finally {
			System.clearProperty("systemProperty");
		}
	}
