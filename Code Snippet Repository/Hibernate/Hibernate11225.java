	@SuppressWarnings({""})
	@Test
	public void testTableNames() {
		assert "secondary_AUD".equals(
				((Iterator<Join>)
						metadata().getEntityBinding(
								"org.hibernate.envers.test.integration.secondary.SecondaryTestEntity_AUD"
						)
								.getJoinIterator())
						.next().getTable().getName()
		);
	}
