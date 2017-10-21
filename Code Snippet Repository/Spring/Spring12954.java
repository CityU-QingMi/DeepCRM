	private void testJsonp(String value, boolean validValue) throws Exception {

		this.request = new MockHttpServletRequest("GET", "/");
		this.request.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);
		this.request.setParameter("c", value);
		this.response = new MockHttpServletResponse();

		HandlerMethod handlerMethod = handlerMethod(new SimpleController(), "handleWithResponseEntity");
		this.handlerAdapter.afterPropertiesSet();
		this.handlerAdapter.handle(this.request, this.response, handlerMethod);

		assertEquals(200, this.response.getStatus());
		if (validValue) {
			assertEquals("/**/" + value + "({\"foo\":\"bar\"});", this.response.getContentAsString());
		}
		else {
			assertEquals("{\"foo\":\"bar\"}", this.response.getContentAsString());
		}
	}
