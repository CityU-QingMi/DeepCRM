	@Test
	public void compareTo() throws Exception {
		ProducesRequestCondition html = new ProducesRequestCondition("text/html");
		ProducesRequestCondition xml = new ProducesRequestCondition("application/xml");
		ProducesRequestCondition none = new ProducesRequestCondition();

		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").header("Accept", "application/xml, text/html").build());

		assertTrue(html.compareTo(xml, exchange) > 0);
		assertTrue(xml.compareTo(html, exchange) < 0);
		assertTrue(xml.compareTo(none, exchange) < 0);
		assertTrue(none.compareTo(xml, exchange) > 0);
		assertTrue(html.compareTo(none, exchange) < 0);
		assertTrue(none.compareTo(html, exchange) > 0);

		exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/").header("Accept", "application/xml, text/*").build());

		assertTrue(html.compareTo(xml, exchange) > 0);
		assertTrue(xml.compareTo(html, exchange) < 0);

		exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/").header("Accept", "application/pdf").build());

		assertTrue(html.compareTo(xml, exchange) == 0);
		assertTrue(xml.compareTo(html, exchange) == 0);

		// See SPR-7000
		exchange = MockServerWebExchange.from(
				MockServerHttpRequest.get("/").header("Accept", "text/html;q=0.9,application/xml").build());

		assertTrue(html.compareTo(xml, exchange) > 0);
		assertTrue(xml.compareTo(html, exchange) < 0);
	}
