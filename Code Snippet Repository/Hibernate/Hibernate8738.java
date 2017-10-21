	@Test
	@TestForIssue(jiraKey = "")
	public void testMetadataWithLocale() {
		try {
			// Rather than building TableMetadata and checking for ascii values in table/column names, simply
			// attempt to validate.
			new SchemaValidator().validate( metadata() );
		}
		catch (HibernateException e) {
			fail("Failed with the Turkish locale, most likely due to the use of String#toLowerCase() within hbm2ddl.  "
					+ "Search for all instaces and replace with StringHelper#toLowerCase(String)!  " + e.getMessage());
		}
	}
