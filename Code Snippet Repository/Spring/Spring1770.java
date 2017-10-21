	@Test
	public void americaChicago() {
		editor.setAsText("America/Chicago");

		ZoneId zoneId = (ZoneId) editor.getValue();
		assertNotNull("The zone ID should not be null.", zoneId);
		assertEquals("The zone ID is not correct.", ZoneId.of("America/Chicago"), zoneId);

		assertEquals("The text version is not correct.", "America/Chicago", editor.getAsText());
	}
