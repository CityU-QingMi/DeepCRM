	@Test
	public void buildContextHierarchyMapForTestClassHierarchyWithMultiLevelContextHierarchies() {
		Map<String, List<ContextConfigurationAttributes>> map = buildContextHierarchyMap(TestClass3WithMultiLevelContextHierarchy.class);

		assertThat(map.size(), is(3));
		assertThat(map.keySet(), hasItems("alpha", "beta", "gamma"));

		List<ContextConfigurationAttributes> alphaConfig = map.get("alpha");
		assertThat(alphaConfig.size(), is(3));
		assertThat(alphaConfig.get(0).getLocations()[0], is("1-A.xml"));
		assertThat(alphaConfig.get(1).getLocations()[0], is("2-A.xml"));
		assertThat(alphaConfig.get(2).getLocations()[0], is("3-A.xml"));

		List<ContextConfigurationAttributes> betaConfig = map.get("beta");
		assertThat(betaConfig.size(), is(3));
		assertThat(betaConfig.get(0).getLocations()[0], is("1-B.xml"));
		assertThat(betaConfig.get(1).getLocations()[0], is("2-B.xml"));
		assertThat(betaConfig.get(2).getLocations()[0], is("3-B.xml"));

		List<ContextConfigurationAttributes> gammaConfig = map.get("gamma");
		assertThat(gammaConfig.size(), is(1));
		assertThat(gammaConfig.get(0).getLocations()[0], is("3-C.xml"));
	}
