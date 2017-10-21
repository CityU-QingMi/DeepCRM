	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testScrollMethod() {
		final String entityName = "expected";
		insertTestEntity( entityName );
		try (Session session = openSession()) {
			final CriteriaQuery<TestEntity> query = createTestEntityCriteriaQuery(
					entityName,
					session
			);
			try (final ScrollableResults scroll = session.createQuery( query ).scroll()) {
				assertThat( scroll.first(), is( true ) );
			}

		}
	}
