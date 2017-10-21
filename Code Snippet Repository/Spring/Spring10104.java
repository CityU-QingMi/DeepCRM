	@Test
	public void splitOneBranches() {
		Flux<XMLEvent> xmlEvents = this.xmlEventDecoder
				.decode(Flux.just(stringBuffer(POJO_ROOT)), null, null, Collections.emptyMap());
		Flux<List<XMLEvent>> result = this.decoder.split(xmlEvents, new QName("pojo"));

		StepVerifier.create(result)
				.consumeNextWith(events -> {
					assertEquals(8, events.size());
					assertStartElement(events.get(0), "pojo");
					assertStartElement(events.get(1), "foo");
					assertCharacters(events.get(2), "foofoo");
					assertEndElement(events.get(3), "foo");
					assertStartElement(events.get(4), "bar");
					assertCharacters(events.get(5), "barbar");
					assertEndElement(events.get(6), "bar");
					assertEndElement(events.get(7), "pojo");
				})
				.expectComplete()
				.verify();
	}
