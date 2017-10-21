	@Test
	public void shouldGetFollowingNexts() throws Exception {
		// Use loadFactor to disable resize
		this.map = new TestWeakConcurrentCache<>(1, 10.0f, 1);
		this.map.put(1, "1");
		this.map.put(2, "2");
		this.map.put(3, "3");
		assertThat(this.map.getSegment(0).getSize(), is(1));
		assertThat(this.map.get(1), is("1"));
		assertThat(this.map.get(2), is("2"));
		assertThat(this.map.get(3), is("3"));
		assertThat(this.map.get(4), is(nullValue()));
	}
