	@Test
	public void testIdentifierReuse() {
		final Integer reusedId = 1;

		EntityManager entityManager = getEntityManager();
		saveUpdateAndRemoveEntity( entityManager, reusedId );
		saveUpdateAndRemoveEntity( entityManager, reusedId );
		entityManager.close();

		assertEquals(
				Arrays.asList( 1, 2, 3, 4, 5, 6 ),
				getAuditReader().getRevisions( IntNoAutoIdTestEntity.class, reusedId )
		);
	}
