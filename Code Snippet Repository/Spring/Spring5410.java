	@Test
	public void shouldSetViaEntrySet() throws Exception {
		this.map.put(1, "1");
		this.map.put(2, "2");
		this.map.put(3, "3");
		Iterator<Map.Entry<Integer, String>> iterator = this.map.entrySet().iterator();
		iterator.next();
		iterator.next().setValue("2b");
		iterator.next();
		assertThat(iterator.hasNext(), is(false));
		assertThat(this.map.size(), is(3));
		assertThat(this.map.get(2), is("2b"));
	}
