	@Test
	public void pathRemainingEnhancements_spr15419() {
		PathPattern pp;
		PathPattern.PathRemainingMatchInfo pri;
		// It would be nice to partially match a path and get any bound variables in one step
		pp = parse("/{this}/{one}/{here}");
		pri = getPathRemaining(pp, "/foo/bar/goo/boo");
		assertEquals("/boo",pri.getPathRemaining().value());
		assertEquals("foo",pri.getUriVariables().get("this"));
		assertEquals("bar",pri.getUriVariables().get("one"));
		assertEquals("goo",pri.getUriVariables().get("here"));
		
		pp = parse("/aaa/{foo}");
		pri = getPathRemaining(pp, "/aaa/bbb");
		assertEquals("",pri.getPathRemaining().value());
		assertEquals("bbb",pri.getUriVariables().get("foo"));

		pp = parse("/aaa/bbb");
		pri = getPathRemaining(pp, "/aaa/bbb");
		assertEquals("",pri.getPathRemaining().value());
		assertEquals(0,pri.getUriVariables().size());
		
		pp = parse("/*/{foo}/b*");
		pri = getPathRemaining(pp, "/foo");
		assertNull(pri);
		pri = getPathRemaining(pp, "/abc/def/bhi");
		assertEquals("",pri.getPathRemaining().value());
		assertEquals("def",pri.getUriVariables().get("foo"));

		pri = getPathRemaining(pp, "/abc/def/bhi/jkl");
		assertEquals("/jkl",pri.getPathRemaining().value());
		assertEquals("def",pri.getUriVariables().get("foo"));
	}
