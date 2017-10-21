	@Test
	public void extractPathWithinPattern() throws Exception {
		checkExtractPathWithinPattern("/welcome*/", "/welcome/", "welcome");
		checkExtractPathWithinPattern("/docs/commit.html", "/docs/commit.html", "");
		checkExtractPathWithinPattern("/docs/*", "/docs/cvs/commit", "cvs/commit");
		checkExtractPathWithinPattern("/docs/cvs/*.html", "/docs/cvs/commit.html", "commit.html");
		checkExtractPathWithinPattern("/docs/**", "/docs/cvs/commit", "cvs/commit");
		checkExtractPathWithinPattern("/doo/{*foobar}", "/doo/customer.html", "customer.html");
		checkExtractPathWithinPattern("/doo/{*foobar}", "/doo/daa/customer.html", "daa/customer.html");
		checkExtractPathWithinPattern("/*.html", "/commit.html", "commit.html");
		checkExtractPathWithinPattern("/docs/*/*/*/*", "/docs/cvs/other/commit.html", "cvs/other/commit.html");
		checkExtractPathWithinPattern("/d?cs/**", "/docs/cvs/commit", "docs/cvs/commit");
		checkExtractPathWithinPattern("/docs/c?s/*.html", "/docs/cvs/commit.html", "cvs/commit.html");
		checkExtractPathWithinPattern("/d?cs/*/*.html", "/docs/cvs/commit.html", "docs/cvs/commit.html");
		checkExtractPathWithinPattern("/a/b/c*d*/*.html", "/a/b/cod/foo.html", "cod/foo.html");
		checkExtractPathWithinPattern("a/{foo}/b/{bar}", "a/c/b/d", "c/b/d");
		checkExtractPathWithinPattern("a/{foo}_{bar}/d/e", "a/b_c/d/e", "b_c/d/e");
		checkExtractPathWithinPattern("aaa//*///ccc///ddd", "aaa//bbb///ccc///ddd", "bbb/ccc/ddd");
		checkExtractPathWithinPattern("aaa//*///ccc///ddd", "aaa//bbb//ccc/ddd", "bbb/ccc/ddd");
		checkExtractPathWithinPattern("aaa/c*/ddd/", "aaa/ccc///ddd///", "ccc/ddd");
		checkExtractPathWithinPattern("", "", "");
		checkExtractPathWithinPattern("/", "", "");
		checkExtractPathWithinPattern("", "/", "");
		checkExtractPathWithinPattern("//", "", "");
		checkExtractPathWithinPattern("", "//", "");
		checkExtractPathWithinPattern("//", "//", "");
		checkExtractPathWithinPattern("//", "/", "");
		checkExtractPathWithinPattern("/", "//", "");
	}
