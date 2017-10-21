	@Test
	public void testSetParameter() throws Exception {
		try (Session session = openSession()) {
			final Query<TestEntity> query = session.createQuery(
					"SELECT e FROM TestEntity e WHERE e.date <= :ts",
					TestEntity.class
			).setParameter( "ts", new DateAttribute( System.currentTimeMillis() ), TemporalType.TIMESTAMP );

			final Stream<TestEntity> stream = query.stream();

			assertThat( stream.count(), is( 1L ) );
		}
	}
