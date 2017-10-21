	@Test
	public void webSphereResourceAdapterSetup() throws Exception {
		Destination destination = new StubQueue();

		DestinationResolver destinationResolver = mock(DestinationResolver.class);
		given(destinationResolver.resolveDestinationName(null, "destinationname", false)).willReturn(destination);

		DefaultJmsActivationSpecFactory activationSpecFactory = new DefaultJmsActivationSpecFactory();
		activationSpecFactory.setDestinationResolver(destinationResolver);

		StubWebSphereActivationSpecImpl spec = (StubWebSphereActivationSpecImpl) activationSpecFactory
				.createActivationSpec(new StubWebSphereResourceAdapterImpl(), activationSpecConfig);

		assertEquals(destination, spec.getDestination());
		assertEquals(5, spec.getMaxConcurrency());
		assertEquals(3, spec.getMaxBatchSize());
	}
