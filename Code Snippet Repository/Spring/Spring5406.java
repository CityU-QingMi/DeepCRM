	@Test
	public void shouldGetValues() throws Exception {
		this.map.put(123, "123");
		this.map.put(456, null);
		this.map.put(null, "789");
		List<String> actual = new ArrayList<>(this.map.values());
		List<String> expected = new ArrayList<>();
		expected.add("123");
		expected.add(null);
		expected.add("789");
		Collections.sort(actual, NULL_SAFE_STRING_SORT);
		Collections.sort(expected, NULL_SAFE_STRING_SORT);
		assertThat(actual, is(expected));
	}
