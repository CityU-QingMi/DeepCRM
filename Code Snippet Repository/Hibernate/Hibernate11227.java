	@SuppressWarnings({""})
	@Test
	public void testTableNames() {
		assert "sec_versions".equals(
				((Iterator<Join>)
						metadata().getEntityBinding(
								"org.hibernate.envers.test.integration.secondary.SecondaryNamingTestEntity_AUD"
						)
								.getJoinIterator())
						.next().getTable().getName()
		);
	}
