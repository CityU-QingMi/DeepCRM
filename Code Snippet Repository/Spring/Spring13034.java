	private void assertContentDisposition(RequestResponseBodyMethodProcessor processor,
			boolean expectContentDisposition, String requestURI, String comment) throws Exception {

		this.servletRequest.setRequestURI(requestURI);
		processor.handleReturnValue("body", this.returnTypeString, this.container, this.request);

		String header = servletResponse.getHeader("Content-Disposition");
		if (expectContentDisposition) {
			assertEquals("Expected 'Content-Disposition' header. Use case: '" + comment + "'",
					"inline;filename=f.txt", header);
		}
		else {
			assertNull("Did not expect 'Content-Disposition' header. Use case: '" + comment + "'", header);
		}

		this.servletRequest = new MockHttpServletRequest();
		this.servletResponse = new MockHttpServletResponse();
		this.request = new ServletWebRequest(servletRequest, servletResponse);
	}
