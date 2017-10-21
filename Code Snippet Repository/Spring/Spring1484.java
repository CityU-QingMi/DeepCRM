	@Test
	public void testFirstFound() throws Exception {
		this.factory.setResolutionMethod(YamlProcessor.ResolutionMethod.FIRST_FOUND);
		this.factory.setResources(new AbstractResource() {
			@Override
			public String getDescription() {
				return "non-existent";
			}
			@Override
			public InputStream getInputStream() throws IOException {
				throw new IOException("planned");
			}
		}, new ByteArrayResource("foo:\n  spam: bar".getBytes()));

		assertEquals(1, this.factory.getObject().size());
	}
