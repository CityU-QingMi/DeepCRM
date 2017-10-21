	private void testFilterInvocation(final ServletException sex) throws Exception {
		final MockHttpServletRequest req = new MockHttpServletRequest();
		req.setAttribute("myAttr", "myValue");
		final MockHttpServletResponse resp = new MockHttpServletResponse();

		// Expect one invocation by the filter being tested
		class DummyFilterChain implements FilterChain {
			public int invocations = 0;
			@Override
			public void doFilter(ServletRequest req, ServletResponse resp) throws IOException, ServletException {
				++invocations;
				if (invocations == 1) {
					assertSame("myValue",
							RequestContextHolder.currentRequestAttributes().getAttribute("myAttr", RequestAttributes.SCOPE_REQUEST));
					if (sex != null) {
						throw sex;
					}
				}
				else {
					throw new IllegalStateException("Too many invocations");
				}
			}
		};

		DummyFilterChain fc = new DummyFilterChain();
		MockFilterConfig mfc = new MockFilterConfig(new MockServletContext(), "foo");

		RequestContextFilter rbf = new RequestContextFilter();
		rbf.init(mfc);

		try {
			rbf.doFilter(req, resp, fc);
			if (sex != null) {
				fail();
			}
		}
		catch (ServletException ex) {
			assertNotNull(sex);
		}

		try {
			RequestContextHolder.currentRequestAttributes();
			fail();
		}
		catch (IllegalStateException ex) {
			// Ok
		}

		assertEquals(1, fc.invocations);
	}
