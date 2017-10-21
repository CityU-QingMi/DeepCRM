	@Test
	public void buildContextHierarchyMapForTestClassHierarchyWithMultiLevelContextHierarchiesAndUnnamedConfig() {
		Map<String, List<ContextConfigurationAttributes>> map = buildContextHierarchyMap(TestClass3WithMultiLevelContextHierarchyAndUnnamedConfig.class);

		String level1 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 1;
		String level2 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 2;
		String level3 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 3;
		String level4 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 4;
		String level5 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 5;
		String level6 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 6;
		String level7 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 7;

		assertThat(map.size(), is(7));
		assertThat(map.keySet(), hasItems(level1, level2, level3, level4, level5, level6, level7));

		List<ContextConfigurationAttributes> level1Config = map.get(level1);
		assertThat(level1Config.size(), is(1));
		assertThat(level1Config.get(0).getLocations()[0], is("1-A.xml"));

		List<ContextConfigurationAttributes> level2Config = map.get(level2);
		assertThat(level2Config.size(), is(1));
		assertThat(level2Config.get(0).getLocations()[0], is("1-B.xml"));

		List<ContextConfigurationAttributes> level3Config = map.get(level3);
		assertThat(level3Config.size(), is(1));
		assertThat(level3Config.get(0).getLocations()[0], is("2-A.xml"));

		// ...

		List<ContextConfigurationAttributes> level7Config = map.get(level7);
		assertThat(level7Config.size(), is(1));
		assertThat(level7Config.get(0).getLocations()[0], is("3-C.xml"));
	}
