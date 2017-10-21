	@Test
	public void viewNames() throws Exception {
		this.resolver.setViewClass(TestView.class);
		this.resolver.setViewNames("my*");

		Mono<View> mono = this.resolver.resolveViewName("my-view", Locale.US);
		assertNotNull(mono.block());

		mono = this.resolver.resolveViewName("not-my-view", Locale.US);
		assertNull(mono.block());
	}
