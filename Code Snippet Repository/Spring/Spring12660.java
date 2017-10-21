	@Test
	public void addRedirectViewController() throws Exception {
		this.registry.addRedirectViewController("/path", "/redirectTo");
		RedirectView redirectView = getRedirectView("/path");
		this.request.setQueryString("a=b");
		this.request.setContextPath("/context");
		redirectView.render(Collections.emptyMap(), this.request, this.response);

		assertEquals(302, this.response.getStatus());
		assertEquals("/context/redirectTo", this.response.getRedirectedUrl());
		assertNotNull(redirectView.getApplicationContext());
	}
