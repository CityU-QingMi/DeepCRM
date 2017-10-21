	@SuppressWarnings("")
	@Test
	public void basicTest() throws Exception {
		URI url = new URI("http://localhost:" + port);
		String header = "SID=31d4d96e407aad42; lang=en-US";
		ResponseEntity<Void> response = new RestTemplate().exchange(
				RequestEntity.get(url).header("Cookie", header).build(), Void.class);

		Map<String, List<HttpCookie>> requestCookies = this.cookieHandler.requestCookies;
		assertEquals(2, requestCookies.size());

		List<HttpCookie> list = requestCookies.get("SID");
		assertEquals(1, list.size());
		assertEquals("31d4d96e407aad42", list.iterator().next().getValue());

		list = requestCookies.get("lang");
		assertEquals(1, list.size());
		assertEquals("en-US", list.iterator().next().getValue());

		List<String> headerValues = response.getHeaders().get("Set-Cookie");
		assertEquals(2, headerValues.size());

		assertThat(splitCookie(headerValues.get(0)), containsInAnyOrder(equalTo("SID=31d4d96e407aad42"),
				equalToIgnoringCase("Path=/"), equalToIgnoringCase("Secure"), equalToIgnoringCase("HttpOnly")));

		assertThat(splitCookie(headerValues.get(1)), containsInAnyOrder(equalTo("lang=en-US"),
				equalToIgnoringCase("Path=/"), equalToIgnoringCase("Domain=example.com")));
	}
