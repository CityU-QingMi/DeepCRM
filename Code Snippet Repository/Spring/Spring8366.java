	@Test
	public void doFilterWithServletAndFilters() throws Exception {
		Servlet servlet = mock(Servlet.class);

		MockFilter filter2 = new MockFilter(servlet);
		MockFilter filter1 = new MockFilter(null);
		MockFilterChain chain = new MockFilterChain(servlet, filter1, filter2);

		chain.doFilter(this.request, this.response);

		assertTrue(filter1.invoked);
		assertTrue(filter2.invoked);

		verify(servlet).service(this.request, this.response);

		try {
			chain.doFilter(this.request, this.response);
			fail("Expected Exception");
		}
		catch (IllegalStateException ex) {
			assertEquals("This FilterChain has already been called!", ex.getMessage());
		}
	}
