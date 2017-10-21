	@Test
	public void render() throws Exception {
		FreeMarkerView view = new FreeMarkerView();
		view.setConfiguration(this.freeMarkerConfig);
		view.setUrl("test.ftl");

		ModelMap model = new ExtendedModelMap();
		model.addAttribute("hello", "hi FreeMarker");
		view.render(model, null, this.exchange).block(Duration.ofMillis(5000));

		StepVerifier.create(this.exchange.getResponse().getBody())
				.consumeNextWith(buf -> assertEquals("<html><body>hi FreeMarker</body></html>", asString(buf)))
				.expectComplete()
				.verify();
	}
