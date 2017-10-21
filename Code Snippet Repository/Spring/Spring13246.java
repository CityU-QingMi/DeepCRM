	@Test
	public void resolveFromCacheWithEncodingVariants() throws IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/js/foo.js");
		request.addHeader("Accept-Encoding", "gzip");
		String file = "js/foo.js";
		Resource resolved = this.resolver.resolveResource(request, file, this.locations);

		String gzFile = file + ".gz";
		Resource gzResource = new ClassPathResource("test/"+gzFile, getClass());
		assertEquals(gzResource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);

		// resolved resource is now cached in CachingResourceResolver

		request = new MockHttpServletRequest("GET", "/js/foo.js");
		resolved = this.resolver.resolveResource(request, file, this.locations);

		Resource resource = new ClassPathResource("test/"+file, getClass());
		assertEquals(resource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertFalse("Expected " + resolved + " to *not* be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
