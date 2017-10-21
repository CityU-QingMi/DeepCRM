	@SuppressWarnings({""})
	@Test
	public void testTableNames() {
		assert "sec_mulid_versions".equals(
				((Iterator<Join>)
						metadata().getEntityBinding(
								"org.hibernate.envers.test.integration.secondary.ids.SecondaryMulIdTestEntity_AUD"
						)
								.getJoinIterator())
						.next().getTable().getName()
		);
	}
