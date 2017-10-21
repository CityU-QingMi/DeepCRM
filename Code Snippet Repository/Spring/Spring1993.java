	@Test
	public void againstDefaultConfiguration() throws Exception {
		ConfigurableMimeFileTypeMap ftm = new ConfigurableMimeFileTypeMap();
		ftm.afterPropertiesSet();

		assertEquals("Invalid content type for HTM", "text/html", ftm.getContentType("foobar.HTM"));
		assertEquals("Invalid content type for html", "text/html", ftm.getContentType("foobar.html"));
		assertEquals("Invalid content type for c++", "text/plain", ftm.getContentType("foobar.c++"));
		assertEquals("Invalid content type for svf", "image/vnd.svf", ftm.getContentType("foobar.svf"));
		assertEquals("Invalid content type for dsf", "image/x-mgx-dsf", ftm.getContentType("foobar.dsf"));
		assertEquals("Invalid default content type", "application/octet-stream", ftm.getContentType("foobar.foo"));
	}
