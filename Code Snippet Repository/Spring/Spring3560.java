	@Test
	public void testReloadableResourceBundleMessageSourceWithNonConcurrentRefresh() throws InterruptedException {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("org/springframework/context/support/messages");
		ms.setCacheSeconds(1);
		ms.setConcurrentRefresh(false);
		// Initial cache attempt
		assertEquals("message1", ms.getMessage("code1", null, Locale.ENGLISH));
		assertEquals("nachricht2", ms.getMessage("code2", null, Locale.GERMAN));
		Thread.sleep(1100);
		// Late enough for a re-cache attempt
		assertEquals("message1", ms.getMessage("code1", null, Locale.ENGLISH));
		assertEquals("nachricht2", ms.getMessage("code2", null, Locale.GERMAN));
	}
