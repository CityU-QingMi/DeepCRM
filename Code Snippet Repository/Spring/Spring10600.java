	@Test
	public void parameters() {
		servletRequest.addParameter("param1", "value1");
		servletRequest.addParameter("param2", "value2");
		servletRequest.addParameter("param2", "value2a");

		assertEquals("value1", request.getParameter("param1"));
		assertEquals(1, request.getParameterValues("param1").length);
		assertEquals("value1", request.getParameterValues("param1")[0]);
		assertEquals("value2", request.getParameter("param2"));
		assertEquals(2, request.getParameterValues("param2").length);
		assertEquals("value2", request.getParameterValues("param2")[0]);
		assertEquals("value2a", request.getParameterValues("param2")[1]);

		Map<String, String[]> paramMap = request.getParameterMap();
		assertEquals(2, paramMap.size());
		assertEquals(1, paramMap.get("param1").length);
		assertEquals("value1", paramMap.get("param1")[0]);
		assertEquals(2, paramMap.get("param2").length);
		assertEquals("value2", paramMap.get("param2")[0]);
		assertEquals("value2a", paramMap.get("param2")[1]);
	}
