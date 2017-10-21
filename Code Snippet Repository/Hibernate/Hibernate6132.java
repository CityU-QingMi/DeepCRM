	@Test
	@TestForIssue(jiraKey = "")
	public void testSelectNonNullLiteralsCastToBoolean() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			final List<Object[]> elements = entityManager.createQuery(
					"SELECT cast( true as boolean ), cast( false as boolean ), e.name FROM MyEntity e",
					Object[].class
			).getResultList();
			Assert.assertEquals( 1, elements.size() );
			Assert.assertEquals( 3, elements.get( 0 ).length );
			Assert.assertEquals( true, elements.get( 0 )[ 0 ] );
			Assert.assertEquals( false, elements.get( 0 )[ 1 ] );
			Assert.assertEquals( "Fab", elements.get( 0 )[ 2 ] );
		}
		finally {
			entityManager.close();
		}
	}
