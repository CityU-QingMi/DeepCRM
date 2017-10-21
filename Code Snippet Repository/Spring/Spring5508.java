	@Test
	public void extractJarFileURL() throws Exception {
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL("jar:file:myjar.jar!/mypath")));
		assertEquals(new URL("file:/myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL(null, "jar:myjar.jar!/mypath", new DummyURLStreamHandler())));
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL(null, "zip:file:myjar.jar!/mypath", new DummyURLStreamHandler())));
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL(null, "wsjar:file:myjar.jar!/mypath", new DummyURLStreamHandler())));

		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL("file:myjar.jar")));
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL("jar:file:myjar.jar!/")));
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL(null, "zip:file:myjar.jar!/", new DummyURLStreamHandler())));
		assertEquals(new URL("file:myjar.jar"),
				ResourceUtils.extractJarFileURL(new URL(null, "wsjar:file:myjar.jar!/", new DummyURLStreamHandler())));
	}
