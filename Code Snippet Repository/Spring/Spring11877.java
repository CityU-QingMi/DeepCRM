	@Test
	public void render() throws Exception {
		Map<String, String> pojoData = new LinkedHashMap<>();
		pojoData.put("foo", "f");
		pojoData.put("bar", "b");
		this.model.addAttribute("pojoData", pojoData);
		this.view.setModelKeys(Collections.singleton("pojoData"));

		this.view.render(this.model, MediaType.APPLICATION_JSON, exchange).block(Duration.ZERO);

		StepVerifier.create(this.exchange.getResponse().getBody())
				.consumeNextWith(buf -> assertEquals("{\"foo\":\"f\",\"bar\":\"b\"}", dumpString(buf)))
				.expectComplete()
				.verify();
	}
