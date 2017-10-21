	@Test
	public void shouldRemoveWhenKeyIsInMap() throws Exception {
		this.map.put(123, null);
		this.map.put(456, "456");
		this.map.put(null, "789");
		assertThat(this.map.remove(123), is(nullValue()));
		assertThat(this.map.remove(456), is("456"));
		assertThat(this.map.remove(null), is("789"));
		assertThat(this.map.isEmpty(), is(true));
	}
