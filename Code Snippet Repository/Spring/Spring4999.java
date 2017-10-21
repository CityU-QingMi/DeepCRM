	@Test
	public void reactiveParameters() throws Exception {

		assertEquals("testObjectMono",
				Conventions.getVariableNameForParameter(getMethodParameter(Mono.class)));

		assertEquals("testObjectFlux",
				Conventions.getVariableNameForParameter(getMethodParameter(Flux.class)));

		assertEquals("testObjectSingle",
				Conventions.getVariableNameForParameter(getMethodParameter(Single.class)));

		assertEquals("testObjectObservable",
				Conventions.getVariableNameForParameter(getMethodParameter(Observable.class)));
	}
