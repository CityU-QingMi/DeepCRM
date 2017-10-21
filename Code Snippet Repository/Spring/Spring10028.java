	@Test
	public void shouldNotCloseConnectionWhenResponseClosed() throws Exception {
		TestByteArrayInputStream is = new TestByteArrayInputStream("Spring".getBytes(StandardCharsets.UTF_8));
		given(this.connection.getErrorStream()).willReturn(null);
		given(this.connection.getInputStream()).willReturn(is);

		InputStream responseStream = this.response.getBody();
		assertThat(StreamUtils.copyToString(responseStream, StandardCharsets.UTF_8), is("Spring"));

		this.response.close();
		assertTrue(is.isClosed());
		verify(this.connection, never()).disconnect();
	}
