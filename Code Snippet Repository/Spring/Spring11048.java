	@Test
	public void removeDuplicateSlashesInPath() throws Exception {
		request.setContextPath("/SPR-12372");
		request.setPathInfo(null);
		request.setServletPath("/foo/bar/");
		request.setRequestURI("/SPR-12372/foo//bar/");

		assertEquals("/foo/bar/", helper.getLookupPathForRequest(request));

		request.setServletPath("/foo/bar/");
		request.setRequestURI("/SPR-12372/foo/bar//");

		assertEquals("/foo/bar/", helper.getLookupPathForRequest(request));

		// "normal" case
		request.setServletPath("/foo/bar//");
		request.setRequestURI("/SPR-12372/foo/bar//");

		assertEquals("/foo/bar//", helper.getLookupPathForRequest(request));
	}
