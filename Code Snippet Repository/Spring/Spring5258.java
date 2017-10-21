	@Test
	@SuppressWarnings("")
	public void collectionsOperations() {
		Map<String, Object> map1 = new HashMap<String, Object>() {{ put("a", "b"); }};
		Map<String, Object> map2 = new HashMap<String, Object>() {{ put("c", "d"); }};

		PropertySource<?> ps1 = new MapPropertySource("ps1", map1);
		ps1.getSource();
		List<PropertySource<?>> propertySources = new ArrayList<>();
		assertThat(propertySources.add(ps1), equalTo(true));
		assertThat(propertySources.contains(ps1), is(true));
		assertThat(propertySources.contains(PropertySource.named("ps1")), is(true));

		PropertySource<?> ps1replacement = new MapPropertySource("ps1", map2); // notice - different map
		assertThat(propertySources.add(ps1replacement), is(true)); // true because linkedlist allows duplicates
		assertThat(propertySources.size(), is(2));
		assertThat(propertySources.remove(PropertySource.named("ps1")), is(true));
		assertThat(propertySources.size(), is(1));
		assertThat(propertySources.remove(PropertySource.named("ps1")), is(true));
		assertThat(propertySources.size(), is(0));

		PropertySource<?> ps2 = new MapPropertySource("ps2", map2);
		propertySources.add(ps1);
		propertySources.add(ps2);
		assertThat(propertySources.indexOf(PropertySource.named("ps1")), is(0));
		assertThat(propertySources.indexOf(PropertySource.named("ps2")), is(1));
		propertySources.clear();
	}
