	@Test
	public void testReloadableResourceBundleMessageSourceWithCommonMessages() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		Properties commonMessages = new Properties();
		commonMessages.setProperty("warning", "Do not do {0}");
		ms.setCommonMessages(commonMessages);
		ms.setBasename("org/springframework/context/support/messages");
		assertEquals("message1", ms.getMessage("code1", null, Locale.ENGLISH));
		assertEquals("nachricht2", ms.getMessage("code2", null, Locale.GERMAN));
		assertEquals("Do not do this", ms.getMessage("warning", new Object[] {"this"}, Locale.ENGLISH));
		assertEquals("Do not do that", ms.getMessage("warning", new Object[] {"that"}, Locale.GERMAN));
	}
