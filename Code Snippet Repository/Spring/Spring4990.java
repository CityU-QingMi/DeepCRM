	@Test
	public void createsMapsCorrectly() {
		// interfaces
		assertThat(createMap(Map.class, 0), is(instanceOf(LinkedHashMap.class)));
		assertThat(createMap(SortedMap.class, 0), is(instanceOf(TreeMap.class)));
		assertThat(createMap(NavigableMap.class, 0), is(instanceOf(TreeMap.class)));
		assertThat(createMap(MultiValueMap.class, 0), is(instanceOf(LinkedMultiValueMap.class)));

		assertThat(createMap(Map.class, String.class, 0), is(instanceOf(LinkedHashMap.class)));
		assertThat(createMap(SortedMap.class, String.class, 0), is(instanceOf(TreeMap.class)));
		assertThat(createMap(NavigableMap.class, String.class, 0), is(instanceOf(TreeMap.class)));
		assertThat(createMap(MultiValueMap.class, String.class, 0), is(instanceOf(LinkedMultiValueMap.class)));

		// concrete types
		assertThat(createMap(HashMap.class, 0), is(instanceOf(HashMap.class)));

		assertThat(createMap(HashMap.class, String.class, 0), is(instanceOf(HashMap.class)));
	}
