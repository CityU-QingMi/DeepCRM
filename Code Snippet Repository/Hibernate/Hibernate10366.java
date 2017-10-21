	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1 )
				.equals(
						getAuditReader().getRevisions(
								Country.class,
								country.getCode()
						)
				);
	}
