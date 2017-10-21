	@Test
	public void testStreamMethod() {
		final String entityName = "expected";
		insertTestEntity( entityName );
		try (Session session = openSession()) {
			final CriteriaQuery<TestEntity> query = createTestEntityCriteriaQuery(
					entityName,
					session
			);
			final Stream<TestEntity> stream = session.createQuery( query ).stream();
			assertThat( stream.count(), is( 1L ) );
		}
	}
