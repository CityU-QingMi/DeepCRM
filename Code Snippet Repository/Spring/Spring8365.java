	@Test
	public void doFilterWithServlet() throws Exception {
		Servlet servlet = mock(Servlet.class);
		MockFilterChain chain = new MockFilterChain(servlet);
		chain.doFilter(this.request, this.response);
		verify(servlet).service(this.request, this.response);
		try {
			chain.doFilter(this.request, this.response);
			fail("Expected Exception");
		}
		catch (IllegalStateException ex) {
			assertEquals("This FilterChain has already been called!", ex.getMessage());
		}
	}
