	@Test
	public void reactiveReturnTypes() throws Exception {

		assertEquals("testObjectMono",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(Mono.class)));

		assertEquals("testObjectFlux",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(Flux.class)));

		assertEquals("testObjectSingle",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(Single.class)));

		assertEquals("testObjectObservable",
				Conventions.getVariableNameForReturnType(getMethodForReturnType(Observable.class)));
	}
