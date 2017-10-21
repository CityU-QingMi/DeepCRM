	@Test
	public void customizedRedirectView() throws Exception {
		this.resolver.setRedirectViewProvider(url -> new RedirectView(url, HttpStatus.FOUND));
		Mono<View> mono = this.resolver.resolveViewName("redirect:foo", Locale.US);

		StepVerifier.create(mono)
				.consumeNextWith(view -> {
					assertEquals(RedirectView.class, view.getClass());
					RedirectView redirectView = (RedirectView) view;
					assertEquals(redirectView.getUrl(), "foo");
					assertEquals(redirectView.getStatusCode(), HttpStatus.FOUND);
				})
				.expectComplete()
				.verify(Duration.ZERO);
	}
