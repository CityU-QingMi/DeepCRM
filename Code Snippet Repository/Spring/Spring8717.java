	@Test
	public void printRequest() throws Exception {
		this.request.addParameter("param", "paramValue");
		this.request.addHeader("header", "headerValue");
		this.request.setCharacterEncoding("UTF-16");
		String palindrome = "ablE was I ere I saw Elba";
		byte[] bytes = palindrome.getBytes("UTF-16");
		this.request.setContent(bytes);
		this.request.getSession().setAttribute("foo", "bar");

		this.handler.handle(this.mvcResult);

		HttpHeaders headers = new HttpHeaders();
		headers.set("header", "headerValue");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("param", "paramValue");

		assertValue("MockHttpServletRequest", "HTTP Method", this.request.getMethod());
		assertValue("MockHttpServletRequest", "Request URI", this.request.getRequestURI());
		assertValue("MockHttpServletRequest", "Parameters", params);
		assertValue("MockHttpServletRequest", "Headers", headers);
		assertValue("MockHttpServletRequest", "Body", palindrome);
		assertValue("MockHttpServletRequest", "Session Attrs", Collections.singletonMap("foo", "bar"));
	}
