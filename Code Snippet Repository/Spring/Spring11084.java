	@Test
	public void pathRemainingCornerCases_spr15336() {
		// No match when the literal path element is a longer form of the segment in the pattern
		assertNull(parse("/foo").matchStartOfPath(toPathContainer("/footastic/bar")));
		assertNull(parse("/f?o").matchStartOfPath(toPathContainer("/footastic/bar")));
		assertNull(parse("/f*o*p").matchStartOfPath(toPathContainer("/flooptastic/bar")));
		assertNull(parse("/{abc}abc").matchStartOfPath(toPathContainer("/xyzabcbar/bar")));

		// With a /** on the end have to check if there is any more data post
		// 'the match' it starts with a separator
		assertNull(parse("/resource/**").matchStartOfPath(toPathContainer("/resourceX")));
		assertEquals("",parse("/resource/**").matchStartOfPath(toPathContainer("/resource")).getPathRemaining().value());

		// Similar to above for the capture-the-rest variant
		assertNull(parse("/resource/{*foo}").matchStartOfPath(toPathContainer("/resourceX")));
		assertEquals("",parse("/resource/{*foo}").matchStartOfPath(toPathContainer("/resource")).getPathRemaining().value());

		PathPattern.PathRemainingMatchInfo pri = parse("/aaa/{bbb}/c?d/e*f/*/g").matchStartOfPath(toPathContainer("/aaa/b/ccd/ef/x/g/i"));
		assertNotNull(pri);
		assertEquals("/i",pri.getPathRemaining().value());
		assertEquals("b",pri.getUriVariables().get("bbb"));

		pri = parse("/aaa/{bbb}/c?d/e*f/*/g/").matchStartOfPath(toPathContainer("/aaa/b/ccd/ef/x/g/i"));
		assertNotNull(pri);
		assertEquals("i",pri.getPathRemaining().value());
		assertEquals("b",pri.getUriVariables().get("bbb"));
		
		pri = parse("/{aaa}_{bbb}/e*f/{x}/g").matchStartOfPath(toPathContainer("/aa_bb/ef/x/g/i"));
		assertNotNull(pri);
		assertEquals("/i",pri.getPathRemaining().value());
		assertEquals("aa",pri.getUriVariables().get("aaa"));
		assertEquals("bb",pri.getUriVariables().get("bbb"));
		assertEquals("x",pri.getUriVariables().get("x"));

		assertNull(parse("/a/b").matchStartOfPath(toPathContainer("")));
		assertEquals("/a/b",parse("").matchStartOfPath(toPathContainer("/a/b")).getPathRemaining().value());
		assertEquals("",parse("").matchStartOfPath(toPathContainer("")).getPathRemaining().value());
	}
