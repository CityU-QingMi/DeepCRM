	@Test
	public void shouldGetKeySet() throws Exception {
		this.map.put(123, "123");
		this.map.put(456, null);
		this.map.put(null, "789");
		Set<Integer> expected = new HashSet<>();
		expected.add(123);
		expected.add(456);
		expected.add(null);
		assertThat(this.map.keySet(), is(expected));
	}
