	@Test
	public void compareTo() {
		ProducesRequestCondition html = new ProducesRequestCondition("text/html");
		ProducesRequestCondition xml = new ProducesRequestCondition("application/xml");
		ProducesRequestCondition none = new ProducesRequestCondition();

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/xml, text/html");

		assertTrue(html.compareTo(xml, request) > 0);
		assertTrue(xml.compareTo(html, request) < 0);
		assertTrue(xml.compareTo(none, request) < 0);
		assertTrue(none.compareTo(xml, request) > 0);
		assertTrue(html.compareTo(none, request) < 0);
		assertTrue(none.compareTo(html, request) > 0);

		request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/xml, text/*");

		assertTrue(html.compareTo(xml, request) > 0);
		assertTrue(xml.compareTo(html, request) < 0);

		request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/pdf");

		assertTrue(html.compareTo(xml, request) == 0);
		assertTrue(xml.compareTo(html, request) == 0);

		// See SPR-7000
		request = new MockHttpServletRequest();
		request.addHeader("Accept", "text/html;q=0.9,application/xml");

		assertTrue(html.compareTo(xml, request) > 0);
		assertTrue(xml.compareTo(html, request) < 0);
	}
