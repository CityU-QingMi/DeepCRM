	@Test
	public void testReloadableResourceBundleMessageSourceFileNameCalculation() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();

		List<String> filenames = ms.calculateFilenamesForLocale("messages", Locale.ENGLISH);
		assertEquals(1, filenames.size());
		assertEquals("messages_en", filenames.get(0));

		filenames = ms.calculateFilenamesForLocale("messages", Locale.UK);
		assertEquals(2, filenames.size());
		assertEquals("messages_en", filenames.get(1));
		assertEquals("messages_en_GB", filenames.get(0));

		filenames = ms.calculateFilenamesForLocale("messages", new Locale("en", "GB", "POSIX"));
		assertEquals(3, filenames.size());
		assertEquals("messages_en", filenames.get(2));
		assertEquals("messages_en_GB", filenames.get(1));
		assertEquals("messages_en_GB_POSIX", filenames.get(0));

		filenames = ms.calculateFilenamesForLocale("messages", new Locale("en", "", "POSIX"));
		assertEquals(2, filenames.size());
		assertEquals("messages_en", filenames.get(1));
		assertEquals("messages_en__POSIX", filenames.get(0));

		filenames = ms.calculateFilenamesForLocale("messages", new Locale("", "UK", "POSIX"));
		assertEquals(2, filenames.size());
		assertEquals("messages__UK", filenames.get(1));
		assertEquals("messages__UK_POSIX", filenames.get(0));

		filenames = ms.calculateFilenamesForLocale("messages", new Locale("", "", "POSIX"));
		assertEquals(0, filenames.size());
	}
