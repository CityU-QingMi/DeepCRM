	@Test
	public void debugViewEnglish() throws Exception {
		View v = rb.resolveViewName("debugView", Locale.ENGLISH);
		assertThat(v, instanceOf(InternalResourceView.class));
		InternalResourceView jv = (InternalResourceView) v;
		assertEquals("debugView must have correct URL", "jsp/debug/debug.jsp", jv.getUrl());

		Map<String, Object> m = jv.getStaticAttributes();
		assertEquals("Must have 2 static attributes", 2, m.size());
		assertEquals("attribute foo", "bar", m.get("foo"));
		assertEquals("attribute postcode", "SE10 9JY", m.get("postcode"));

		assertEquals("Correct default content type", AbstractView.DEFAULT_CONTENT_TYPE, jv.getContentType());
	}
