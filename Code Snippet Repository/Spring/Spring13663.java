	@Test
	public void explicitStatusCodeHttp11() throws Exception {
		RedirectView rv = new RedirectView();
		rv.setUrl("http://url.somewhere.com");
		rv.setHttp10Compatible(false);
		rv.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		rv.render(new HashMap<>(), request, response);
		assertEquals(301, response.getStatus());
		assertEquals("http://url.somewhere.com", response.getHeader("Location"));
	}
