	@Test
	public void withAdditionalMappings() throws Exception {
		ConfigurableMimeFileTypeMap ftm = new ConfigurableMimeFileTypeMap();
		ftm.setMappings(new String[] {"foo/bar HTM foo", "foo/cpp c++"});
		ftm.afterPropertiesSet();

		assertEquals("Invalid content type for HTM - override didn't work", "foo/bar", ftm.getContentType("foobar.HTM"));
		assertEquals("Invalid content type for c++ - override didn't work", "foo/cpp", ftm.getContentType("foobar.c++"));
		assertEquals("Invalid content type for foo - new mapping didn't work", "foo/bar", ftm.getContentType("bar.foo"));
	}
