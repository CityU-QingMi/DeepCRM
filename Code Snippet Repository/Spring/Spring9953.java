	@Test
	public void parseDates() {
		ContentDisposition disposition = ContentDisposition
				.parse("attachment; creation-date=\"Mon, 12 Feb 2007 10:15:30 -0500\"; modification-date=\"Tue, 13 Feb 2007 10:15:30 -0500\"; read-date=\"Wed, 14 Feb 2007 10:15:30 -0500\"");
		assertEquals(ContentDisposition.builder("attachment")
				.creationDate(ZonedDateTime.parse("Mon, 12 Feb 2007 10:15:30 -0500", DateTimeFormatter.RFC_1123_DATE_TIME))
				.modificationDate(ZonedDateTime.parse("Tue, 13 Feb 2007 10:15:30 -0500", DateTimeFormatter.RFC_1123_DATE_TIME))
				.readDate(ZonedDateTime.parse("Wed, 14 Feb 2007 10:15:30 -0500", DateTimeFormatter.RFC_1123_DATE_TIME))
						.build(), disposition);
	}
