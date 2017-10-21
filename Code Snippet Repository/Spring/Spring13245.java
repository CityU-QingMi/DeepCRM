	@Test
	public void resolveFingerprintedGzippedFile() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept-Encoding", "gzip");
		String file = "foo-e36d2e05253c6c7085a91522ce43a0b4.css";
		Resource resolved = this.resolver.resolveResource(request, file, this.locations);

		String gzFile = file + ".gz";
		Resource resource = new ClassPathResource("test/"+gzFile, getClass());
		assertEquals(resource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/"+file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
