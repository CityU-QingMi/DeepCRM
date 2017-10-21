	@Test
	public void pathRemainderBasicCases_spr15336() {
		// Cover all PathElement kinds
		assertEquals("/bar", getPathRemaining("/foo","/foo/bar").getPathRemaining().value());
		assertEquals("/", getPathRemaining("/foo","/foo/").getPathRemaining().value());
		assertEquals("/bar",getPathRemaining("/foo*","/foo/bar").getPathRemaining().value());
		assertEquals("/bar", getPathRemaining("/*","/foo/bar").getPathRemaining().value());
		assertEquals("/bar", getPathRemaining("/{foo}","/foo/bar").getPathRemaining().value());
		assertNull(getPathRemaining("/foo","/bar/baz"));
		assertEquals("",getPathRemaining("/**","/foo/bar").getPathRemaining().value());
		assertEquals("",getPathRemaining("/{*bar}","/foo/bar").getPathRemaining().value());
		assertEquals("/bar",getPathRemaining("/a?b/d?e","/aab/dde/bar").getPathRemaining().value());
		assertEquals("/bar",getPathRemaining("/{abc}abc","/xyzabc/bar").getPathRemaining().value());
		assertEquals("/bar",getPathRemaining("/*y*","/xyzxyz/bar").getPathRemaining().value());
		assertEquals("",getPathRemaining("/","/").getPathRemaining().value());
		assertEquals("a",getPathRemaining("/","/a").getPathRemaining().value());
		assertEquals("a/",getPathRemaining("/","/a/").getPathRemaining().value());
		assertEquals("/bar",getPathRemaining("/a{abc}","/a/bar").getPathRemaining().value());
		assertEquals("/bar", getPathRemaining("/foo//","/foo///bar").getPathRemaining().value());
	}
