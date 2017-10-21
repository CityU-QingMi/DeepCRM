	@Test
	public void fromCurrentRequest() {
		this.request.setRequestURI("/mvc-showcase/data/param");
		this.request.setQueryString("foo=123");
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(this.request));
		try {
			String result = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
			assertEquals("http://localhost/mvc-showcase/data/param?foo=123", result);
		}
		finally {
			RequestContextHolder.resetRequestAttributes();
		}
	}
