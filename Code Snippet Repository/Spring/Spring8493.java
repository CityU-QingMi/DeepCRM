	@Test
	public void buildContextHierarchyMapForTestClassHierarchyWithMultiLevelContextHierarchiesAndOverriddenInitializers() {
		Map<String, List<ContextConfigurationAttributes>> map = buildContextHierarchyMap(TestClass2WithMultiLevelContextHierarchyWithOverriddenInitializers.class);

		assertThat(map.size(), is(2));
		assertThat(map.keySet(), hasItems("alpha", "beta"));

		List<ContextConfigurationAttributes> alphaConfig = map.get("alpha");
		assertThat(alphaConfig.size(), is(2));
		assertThat(alphaConfig.get(0).getLocations().length, is(1));
		assertThat(alphaConfig.get(0).getLocations()[0], is("1-A.xml"));
		assertThat(alphaConfig.get(0).getInitializers().length, is(0));
		assertThat(alphaConfig.get(1).getLocations().length, is(0));
		assertThat(alphaConfig.get(1).getInitializers().length, is(1));
		assertEquals(DummyApplicationContextInitializer.class, alphaConfig.get(1).getInitializers()[0]);

		List<ContextConfigurationAttributes> betaConfig = map.get("beta");
		assertThat(betaConfig.size(), is(2));
		assertThat(betaConfig.get(0).getLocations().length, is(1));
		assertThat(betaConfig.get(0).getLocations()[0], is("1-B.xml"));
		assertThat(betaConfig.get(0).getInitializers().length, is(0));
		assertThat(betaConfig.get(1).getLocations().length, is(0));
		assertThat(betaConfig.get(1).getInitializers().length, is(1));
		assertEquals(DummyApplicationContextInitializer.class, betaConfig.get(1).getInitializers()[0]);
	}
