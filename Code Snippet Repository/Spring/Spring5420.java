	@Test
	public void shouldClear() throws Exception {
		this.map.put(123, "123");
		this.map.put(456, null);
		this.map.put(null, "789");
		this.map.clear();
		assertThat(this.map.size(), is(0));
		assertThat(this.map.containsKey(123), is(false));
		assertThat(this.map.containsKey(456), is(false));
		assertThat(this.map.containsKey(null), is(false));
	}
