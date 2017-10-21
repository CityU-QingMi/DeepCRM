	@Test
	public void addRedirectViewControllerWithCustomSettings() throws Exception {
		this.registry.addRedirectViewController("/path", "/redirectTo")
				.setContextRelative(false)
				.setKeepQueryParams(true)
				.setStatusCode(HttpStatus.PERMANENT_REDIRECT);

		RedirectView redirectView = getRedirectView("/path");
		this.request.setQueryString("a=b");
		this.request.setContextPath("/context");
		redirectView.render(Collections.emptyMap(), this.request, this.response);

		assertEquals(308, this.response.getStatus());
		assertEquals("/redirectTo?a=b", response.getRedirectedUrl());
		assertNotNull(redirectView.getApplicationContext());
	}
