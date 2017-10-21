	@Test
	public void registerNullContainerFactoryWithNoDefault() throws Exception {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("some id");
		this.registrar.registerEndpoint(endpoint, null);

		this.thrown.expect(IllegalStateException.class);
		this.thrown.expectMessage(endpoint.toString());
		this.registrar.afterPropertiesSet();
	}
