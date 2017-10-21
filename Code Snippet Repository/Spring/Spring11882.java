	@Test
	public void redirectView() throws Exception {
		Mono<View> mono = this.resolver.resolveViewName("redirect:foo", Locale.US);

		StepVerifier.create(mono)
				.consumeNextWith(view -> {
					assertEquals(RedirectView.class, view.getClass());
					RedirectView redirectView = (RedirectView) view;
					assertEquals(redirectView.getUrl(), "foo");
					assertEquals(redirectView.getStatusCode(), HttpStatus.SEE_OTHER);
				})
				.expectComplete()
				.verify(Duration.ZERO);
	}
