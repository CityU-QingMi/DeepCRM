	@Test
	public void path() throws Exception {
		// basic
		testPath("/a/b/c", "/a/b/c", Arrays.asList("/", "a", "/", "b", "/", "c"));

		// root path
		testPath("/", "/", Collections.singletonList("/"));

		// empty path
		testPath("", "", Collections.emptyList());
		testPath("%20%20", "%20%20", Collections.singletonList("%20%20"));

		// trailing slash
		testPath("/a/b/", "/a/b/", Arrays.asList("/", "a", "/", "b", "/"));
		testPath("/a/b//", "/a/b//", Arrays.asList("/", "a", "/", "b", "/", "/"));

		// extra slashes and spaces
		testPath("/%20", "/%20", Arrays.asList("/", "%20"));
		testPath("//%20/%20", "//%20/%20", Arrays.asList("/", "/", "%20", "/", "%20"));
	}
