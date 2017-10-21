	@Test
	public void requestPath() throws Exception {
		// basic
		testRequestPath("/app/a/b/c", "/app", "/a/b/c");

		// no context path
		testRequestPath("/a/b/c", "", "/a/b/c");

		// context path only
		testRequestPath("/a/b", "/a/b", "");

		// root path
		testRequestPath("/", "", "/");

		// empty path
		testRequestPath("", "", "");
		testRequestPath("", "/", "");

		// trailing slash
		testRequestPath("/app/a/", "/app", "/a/");
		testRequestPath("/app/a//", "/app", "/a//");
	}
