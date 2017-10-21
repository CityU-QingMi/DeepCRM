	@Test
	public void addAnnotatedEndpointClassesWithExplicitServerContainerOnly() throws Exception {
		this.exporter.setAnnotatedEndpointClasses(AnnotatedDummyEndpoint.class, AnnotatedDummyEndpointBean.class);
		this.exporter.setServerContainer(this.serverContainer);
		this.exporter.afterPropertiesSet();
		this.exporter.afterSingletonsInstantiated();

		verify(this.serverContainer).addEndpoint(AnnotatedDummyEndpoint.class);
		verify(this.serverContainer).addEndpoint(AnnotatedDummyEndpointBean.class);
	}
