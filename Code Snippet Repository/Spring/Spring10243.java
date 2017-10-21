	@Test
	public void subPath() throws Exception {
		// basic
		PathContainer path = PathContainer.parsePath("/a/b/c");
		assertSame(path, path.subPath(0));
		assertEquals("/b/c", path.subPath(2).value());
		assertEquals("/c", path.subPath(4).value());

		// root path
		path = PathContainer.parsePath("/");
		assertEquals("/", path.subPath(0).value());

		// trailing slash
		path = PathContainer.parsePath("/a/b/");
		assertEquals("/b/", path.subPath(2).value());
	}
