	@Test
	public void testMessageSourceResourceBundle() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("org/springframework/context/support/messages");
		MessageSourceResourceBundle rbe = new MessageSourceResourceBundle(ms, Locale.ENGLISH);
		assertEquals("message1", rbe.getString("code1"));
		assertTrue(rbe.containsKey("code1"));
		MessageSourceResourceBundle rbg = new MessageSourceResourceBundle(ms, Locale.GERMAN);
		assertEquals("nachricht2", rbg.getString("code2"));
		assertTrue(rbg.containsKey("code2"));
	}
