	@Test
	public void resolveWithNullRequest() throws IOException {
		String file = "js/foo.js";
		Resource resolved = this.resolver.resolveResource(null, file, this.locations);

		String gzFile = file+".gz";
		Resource gzResource = new ClassPathResource("test/"+gzFile, getClass());
		assertEquals(gzResource.getDescription(), resolved.getDescription());
		assertEquals(new ClassPathResource("test/" + file).getFilename(), resolved.getFilename());
		assertTrue("Expected " + resolved + " to be of type " + HttpResource.class,
				resolved instanceof HttpResource);
	}
