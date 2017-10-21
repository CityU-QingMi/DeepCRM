	@Test
	public void registerNullContainerFactoryIsAllowed() throws Exception {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("some id");
		this.registrar.setContainerFactory(this.containerFactory);
		this.registrar.registerEndpoint(endpoint, null);
		this.registrar.afterPropertiesSet();
		assertNotNull("Container not created", this.registry.getListenerContainer("some id"));
		assertEquals(1, this.registry.getListenerContainers().size());
		assertEquals("some id", this.registry.getListenerContainerIds().iterator().next());
	}
