	@Test
	public void doFilterEmptyChain() throws Exception {
		MockFilterChain chain = new MockFilterChain();
		chain.doFilter(this.request, this.response);

		assertThat(chain.getRequest(), is(request));
		assertThat(chain.getResponse(), is(response));

		try {
			chain.doFilter(this.request, this.response);
			fail("Expected Exception");
		}
		catch (IllegalStateException ex) {
			assertEquals("This FilterChain has already been called!", ex.getMessage());
		}
	}
