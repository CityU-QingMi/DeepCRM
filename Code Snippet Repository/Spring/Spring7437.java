	@Test
	public void resolveNameFromSystemProperty() throws Exception {
		System.setProperty("systemProperty", "sysbar");
		try {
			Message<byte[]> message = MessageBuilder.withPayload(new byte[0]).setHeader("sysbar", "foo").build();
			Object result = resolver.resolveArgument(paramSystemPropertyName, message);
			assertEquals("foo", result);
		}
		finally {
			System.clearProperty("systemProperty");
		}
	}
