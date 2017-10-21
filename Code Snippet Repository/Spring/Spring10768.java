	@Test
	public void caseInsensitiveForwardedPrefix() throws Exception {
		this.request = new MockHttpServletRequest() {
			// Make it case-sensitive (SPR-14372)
			@Override
			public String getHeader(String header) {
				Enumeration<String> names = getHeaderNames();
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					if (name.equals(header)) {
						return super.getHeader(header);
					}
				}
				return null;
			}
		};
		this.request.addHeader(X_FORWARDED_PREFIX, "/prefix");
		this.request.setRequestURI("/path");
		HttpServletRequest actual = filterAndGetWrappedRequest();

		assertEquals("/prefix/path", actual.getRequestURI());
	}
