	@Test
	public void shouldDrainStreamWhenResponseClosed() throws Exception {
		byte[] buf = new byte[6];
		TestByteArrayInputStream is = new TestByteArrayInputStream("SpringSpring".getBytes(StandardCharsets.UTF_8));
		given(this.connection.getErrorStream()).willReturn(null);
		given(this.connection.getInputStream()).willReturn(is);

		InputStream responseStream = this.response.getBody();
		responseStream.read(buf);
		assertThat(new String(buf, StandardCharsets.UTF_8), is("Spring"));
		assertThat(is.available(), is(6));

		this.response.close();
		assertThat(is.available(), is(0));
		assertTrue(is.isClosed());
		verify(this.connection, never()).disconnect();
	}
