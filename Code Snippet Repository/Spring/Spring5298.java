	@Test
	public void testContentLength() throws IOException {
		AbstractResource resource = new AbstractResource() {
			@Override
			public InputStream getInputStream() throws IOException {
				return new ByteArrayInputStream(new byte[] { 'a', 'b', 'c' });
			}
			@Override
			public String getDescription() {
				return null;
			}
		};
		assertThat(resource.contentLength(), is(3L));
	}
