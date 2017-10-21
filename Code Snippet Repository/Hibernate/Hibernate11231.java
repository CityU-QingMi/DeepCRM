	@SuppressWarnings({""})
	@Test
	public void testTableNames() {
		assert "sec_embid_versions".equals(
				((Iterator<Join>)
						metadata().getEntityBinding(
								"org.hibernate.envers.test.integration.secondary.ids.SecondaryEmbIdTestEntity_AUD"
						).getJoinIterator()).next().getTable().getName()
		);
	}
