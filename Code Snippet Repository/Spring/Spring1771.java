	@Test
	public void americaLosAngeles() {
		editor.setAsText("America/Los_Angeles");

		ZoneId zoneId = (ZoneId) editor.getValue();
		assertNotNull("The zone ID should not be null.", zoneId);
		assertEquals("The zone ID is not correct.", ZoneId.of("America/Los_Angeles"), zoneId);

		assertEquals("The text version is not correct.", "America/Los_Angeles", editor.getAsText());
	}
