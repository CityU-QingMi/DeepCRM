	public void testPattern() {
		
		Pattern p = WildcardUtil.compileWildcardPattern("a*b");
		assertTrue(p.matcher("ab").matches());
		assertTrue(p.matcher("axyb").matches());
		assertFalse(p.matcher("bxyb").matches());
		
		p = WildcardUtil.compileWildcardPattern("a\\*b");
		assertFalse(p.matcher("ab").matches());
		assertTrue(p.matcher("a*b").matches());
		
		p = WildcardUtil.compileWildcardPattern("a.*");
		assertFalse(p.matcher("ab").matches());
		assertFalse(p.matcher("ab.b").matches());
		assertTrue(p.matcher("a.b").matches());
		assertTrue(p.matcher("a.bc").matches());
		assertTrue(p.matcher("a.b.c").matches());
		
		p = WildcardUtil.compileWildcardPattern("a[*]");
		assertFalse(p.matcher("ab").matches());
		assertFalse(p.matcher("ab[b]").matches());
		assertTrue(p.matcher("a[b]").matches());
		assertTrue(p.matcher("a[bc]").matches());
		assertFalse(p.matcher("a[b].c").matches());

		p = WildcardUtil.compileWildcardPattern("a[*].*");
		assertTrue(p.matcher("a[b].c").matches());
		assertTrue(p.matcher("a[bc].cd").matches());
	}
