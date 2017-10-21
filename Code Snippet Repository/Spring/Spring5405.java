	@Test
	public void shouldPutAndGet() throws Exception {
		// NOTE we are using mock references so we don't need to worry about GC
		assertThat(this.map.size(), is(0));
		this.map.put(123, "123");
		assertThat(this.map.get(123), is("123"));
		assertThat(this.map.size(), is(1));
		this.map.put(123, "123b");
		assertThat(this.map.size(), is(1));
		this.map.put(123, null);
		assertThat(this.map.size(), is(1));
	}
