	@Test
	public void createsCollectionsCorrectly() {
		// interfaces
		assertThat(createCollection(List.class, 0), is(instanceOf(ArrayList.class)));
		assertThat(createCollection(Set.class, 0), is(instanceOf(LinkedHashSet.class)));
		assertThat(createCollection(Collection.class, 0), is(instanceOf(LinkedHashSet.class)));
		assertThat(createCollection(SortedSet.class, 0), is(instanceOf(TreeSet.class)));
		assertThat(createCollection(NavigableSet.class, 0), is(instanceOf(TreeSet.class)));

		assertThat(createCollection(List.class, String.class, 0), is(instanceOf(ArrayList.class)));
		assertThat(createCollection(Set.class, String.class, 0), is(instanceOf(LinkedHashSet.class)));
		assertThat(createCollection(Collection.class, String.class, 0), is(instanceOf(LinkedHashSet.class)));
		assertThat(createCollection(SortedSet.class, String.class, 0), is(instanceOf(TreeSet.class)));
		assertThat(createCollection(NavigableSet.class, String.class, 0), is(instanceOf(TreeSet.class)));

		// concrete types
		assertThat(createCollection(HashSet.class, 0), is(instanceOf(HashSet.class)));
		assertThat(createCollection(HashSet.class, String.class, 0), is(instanceOf(HashSet.class)));
	}
