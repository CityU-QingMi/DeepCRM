	@Test
	public void registerContainerWithoutFactory() throws Exception {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("myEndpoint");
		this.registrar.setContainerFactory(this.containerFactory);
		this.registrar.registerEndpoint(endpoint);
		this.registrar.afterPropertiesSet();
		assertNotNull("Container not created", this.registry.getListenerContainer("myEndpoint"));
		assertEquals(1, this.registry.getListenerContainers().size());
		assertEquals("myEndpoint", this.registry.getListenerContainerIds().iterator().next());
	}
