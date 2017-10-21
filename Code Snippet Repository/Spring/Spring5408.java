	@Test
	public void shouldGetEntrySetFollowingNext() throws Exception {
		// Use loadFactor to disable resize
		this.map = new TestWeakConcurrentCache<>(1, 10.0f, 1);
		this.map.put(1, "1");
		this.map.put(2, "2");
		this.map.put(3, "3");
		HashMap<Integer, String> expected = new HashMap<>();
		expected.put(1, "1");
		expected.put(2, "2");
		expected.put(3, "3");
		assertThat(this.map.entrySet(), is(expected.entrySet()));
	}
