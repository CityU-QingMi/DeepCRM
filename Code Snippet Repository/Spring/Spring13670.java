	@Test
	public void singleParamWithoutExposingModelAttributes() throws Exception {
		String url = "http://url.somewhere.com";
		Map<String, String> model = Collections.singletonMap("foo", "bar");

		TestRedirectView rv = new TestRedirectView(url, false, model);
		rv.setExposeModelAttributes(false);
		rv.render(model, request, response);

		assertEquals(url, this.response.getRedirectedUrl());
	}
