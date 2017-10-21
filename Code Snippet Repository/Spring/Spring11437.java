	@Test
	public void cookies() throws Exception {
		ResponseCookie cookie = ResponseCookie.from("foo", "bar").build();
		MultiValueMap<String, ResponseCookie> cookies = new LinkedMultiValueMap<>();
		cookies.add("foo", cookie);

		when(mockResponse.getCookies()).thenReturn(cookies);

		assertSame(cookies, defaultClientResponse.cookies());
	}
