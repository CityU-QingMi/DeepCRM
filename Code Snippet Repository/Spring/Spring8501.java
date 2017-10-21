	@Test
	public void buildContextHierarchyMapForTestClassHierarchyWithMultiLevelContextHierarchiesAndPartiallyNamedConfig() {
		Map<String, List<ContextConfigurationAttributes>> map = buildContextHierarchyMap(TestClass2WithMultiLevelContextHierarchyAndPartiallyNamedConfig.class);

		String level1 = "parent";
		String level2 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 2;
		String level3 = GENERATED_CONTEXT_HIERARCHY_LEVEL_PREFIX + 3;

		assertThat(map.size(), is(3));
		assertThat(map.keySet(), hasItems(level1, level2, level3));
		Iterator<String> levels = map.keySet().iterator();
		assertThat(levels.next(), is(level1));
		assertThat(levels.next(), is(level2));
		assertThat(levels.next(), is(level3));

		List<ContextConfigurationAttributes> level1Config = map.get(level1);
		assertThat(level1Config.size(), is(2));
		assertThat(level1Config.get(0).getLocations()[0], is("1-A.xml"));
		assertThat(level1Config.get(1).getLocations()[0], is("2-A.xml"));

		List<ContextConfigurationAttributes> level2Config = map.get(level2);
		assertThat(level2Config.size(), is(1));
		assertThat(level2Config.get(0).getLocations()[0], is("1-B.xml"));

		List<ContextConfigurationAttributes> level3Config = map.get(level3);
		assertThat(level3Config.size(), is(1));
		assertThat(level3Config.get(0).getLocations()[0], is("2-C.xml"));
	}
