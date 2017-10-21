	@Test
	public void shouldPutAll() throws Exception {
		Map<Integer, String> m = new HashMap<>();
		m.put(123, "123");
		m.put(456, null);
		m.put(null, "789");
		this.map.putAll(m);
		assertThat(this.map.size(), is(3));
		assertThat(this.map.get(123), is("123"));
		assertThat(this.map.get(456), is(nullValue()));
		assertThat(this.map.get(null), is("789"));
	}
