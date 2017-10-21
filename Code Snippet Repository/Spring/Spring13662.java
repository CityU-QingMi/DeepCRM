	@Test
	public void propagateQueryParams() throws Exception {
		RedirectView rv = new RedirectView();
		rv.setPropagateQueryParams(true);
		rv.setUrl("http://url.somewhere.com?foo=bar#bazz");
		request.setQueryString("a=b&c=d");
		rv.render(new HashMap<>(), request, response);
		assertEquals(302, response.getStatus());
		assertEquals("http://url.somewhere.com?foo=bar&a=b&c=d#bazz", response.getHeader("Location"));
	}
