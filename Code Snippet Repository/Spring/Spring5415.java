	@Test
	public void shouldResize() throws Exception {
		this.map = new TestWeakConcurrentCache<>(1, 0.75f, 1);
		this.map.put(1, "1");
		assertThat(this.map.getSegment(0).getSize(), is(1));
		assertThat(this.map.get(1), is("1"));

		this.map.put(2, "2");
		assertThat(this.map.getSegment(0).getSize(), is(2));
		assertThat(this.map.get(1), is("1"));
		assertThat(this.map.get(2), is("2"));

		this.map.put(3, "3");
		assertThat(this.map.getSegment(0).getSize(), is(4));
		assertThat(this.map.get(1), is("1"));
		assertThat(this.map.get(2), is("2"));
		assertThat(this.map.get(3), is("3"));

		this.map.put(4, "4");
		assertThat(this.map.getSegment(0).getSize(), is(8));
		assertThat(this.map.get(4), is("4"));

		// Putting again should not increase the count
		for (int i = 1; i <= 5; i++) {
			this.map.put(i, String.valueOf(i));
		}
		assertThat(this.map.getSegment(0).getSize(), is(8));
		assertThat(this.map.get(5), is("5"));
	}
