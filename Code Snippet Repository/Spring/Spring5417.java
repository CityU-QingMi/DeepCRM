	@Test
	public void shouldPergeOnPut() throws Exception {
		this.map = new TestWeakConcurrentCache<>(1, 0.75f, 1);
		for (int i = 1; i <= 5; i++) {
			this.map.put(i, String.valueOf(i));
		}
		this.map.getMockReference(1, Restructure.NEVER).queueForPurge();
		this.map.getMockReference(3, Restructure.NEVER).queueForPurge();
		this.map.put(1, "1");
		assertThat(this.map.get(1), is("1"));
		assertThat(this.map.get(2), is("2"));
		assertThat(this.map.getReference(3, Restructure.WHEN_NECESSARY), is(nullValue()));
		assertThat(this.map.get(4), is("4"));
		assertThat(this.map.get(5), is("5"));
	}
