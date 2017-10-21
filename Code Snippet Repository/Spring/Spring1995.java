	@Test
	public void withCustomMappingLocation() throws Exception {
		Resource resource = new ClassPathResource("test.mime.types", getClass());

		ConfigurableMimeFileTypeMap ftm = new ConfigurableMimeFileTypeMap();
		ftm.setMappingLocation(resource);
		ftm.afterPropertiesSet();

		assertEquals("Invalid content type for foo", "text/foo", ftm.getContentType("foobar.foo"));
		assertEquals("Invalid content type for bar", "text/bar", ftm.getContentType("foobar.bar"));
		assertEquals("Invalid content type for fimg", "image/foo", ftm.getContentType("foobar.fimg"));
		assertEquals("Invalid content type for bimg", "image/bar", ftm.getContentType("foobar.bimg"));
	}
