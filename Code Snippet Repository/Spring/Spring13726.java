	private void testJsonp(String paramName, String paramValue, boolean validValue) throws Exception {
		Map<String, Object> model = new HashMap<>();
		model.put("foo", "bar");

		this.request = new MockHttpServletRequest();
		this.request.addParameter("otherparam", "value");
		this.request.addParameter(paramName, paramValue);
		this.response = new MockHttpServletResponse();

		this.view.render(model, this.request, this.response);

		String content = this.response.getContentAsString();
		if (validValue) {
			assertEquals("/**/" + paramValue + "({\"foo\":\"bar\"});", content);
		}
		else {
			assertEquals("{\"foo\":\"bar\"}", content);
		}
	}
