	@Test
	public void resolveGzippedFile() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept-Encoding", "gzip");
		String file = "js/foo.js";
		Resource resolved = this.resolver.resolveResource(request, file, this.locations);

		String gzFile = file + ".gz";
		Resource resource = new ClassPathResource("test/"+gzFile, getClass());
		assertEquals(resource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
