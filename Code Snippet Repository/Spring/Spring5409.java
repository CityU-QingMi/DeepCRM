	@Test
	public void shouldRemoveViaEntrySet() throws Exception {
		this.map.put(1, "1");
		this.map.put(2, "2");
		this.map.put(3, "3");
		Iterator<Map.Entry<Integer, String>> iterator = this.map.entrySet().iterator();
		iterator.next();
		iterator.next();
		iterator.remove();
		iterator.next();
		assertThat(iterator.hasNext(), is(false));
		assertThat(this.map.size(), is(2));
		assertThat(this.map.containsKey(2), is(false));
	}
