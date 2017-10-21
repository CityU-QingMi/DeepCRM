	@Test
	public void testNumber() throws Exception {
		String composerDouble = "/ns:people/composers/composer[%s]/someDouble";

		this.mockServer.expect(requestTo("/composers"))
			.andExpect(content().contentType("application/xml"))
			.andExpect(xpath(composerDouble, NS, 1).number(21d))
			.andExpect(xpath(composerDouble, NS, 2).number(.0025))
			.andExpect(xpath(composerDouble, NS, 3).number(1.6035))
			.andExpect(xpath(composerDouble, NS, 4).number(Double.NaN))
			.andExpect(xpath(composerDouble, NS, 1).number(equalTo(21d))) // Hamcrest..
			.andExpect(xpath(composerDouble, NS, 3).number(closeTo(1.6, .01))) // Hamcrest..
			.andRespond(withSuccess());

		this.restTemplate.put(new URI("/composers"), this.people);
		this.mockServer.verify();
	}
