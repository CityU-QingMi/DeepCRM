	@Test
	public void toXMLEventsAalto() {

		Flux<XMLEvent> events =
				this.decoder.decode(Flux.just(stringBuffer(XML)), null, null, Collections.emptyMap());

		StepVerifier.create(events)
				.consumeNextWith(e -> assertTrue(e.isStartDocument()))
				.consumeNextWith(e -> assertStartElement(e, "pojo"))
				.consumeNextWith(e -> assertStartElement(e, "foo"))
				.consumeNextWith(e -> assertCharacters(e, "foofoo"))
				.consumeNextWith(e -> assertEndElement(e, "foo"))
				.consumeNextWith(e -> assertStartElement(e, "bar"))
				.consumeNextWith(e -> assertCharacters(e, "barbar"))
				.consumeNextWith(e -> assertEndElement(e, "bar"))
				.consumeNextWith(e -> assertEndElement(e, "pojo"))
				.expectComplete()
				.verify();
	}
