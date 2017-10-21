	@Test
	public void testUrlResource() throws IOException {
		Resource resource = new UrlResource(getClass().getResource("Resource.class"));
		doTestResource(resource);
		assertEquals(new UrlResource(getClass().getResource("Resource.class")), resource);

		Resource resource2 = new UrlResource("file:core/io/Resource.class");
		assertEquals(resource2, new UrlResource("file:core/../core/io/./Resource.class"));

		assertEquals("test.txt", new UrlResource("file:/dir/test.txt?argh").getFilename());
		assertEquals("test.txt", new UrlResource("file:\\dir\\test.txt?argh").getFilename());
		assertEquals("test.txt", new UrlResource("file:\\dir/test.txt?argh").getFilename());
	}
