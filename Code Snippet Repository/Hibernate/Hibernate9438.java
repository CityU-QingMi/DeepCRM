	@Test
	public void testWithoutCommaFail() {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				String result = entityManager.createQuery(
						"select FUNCTION('substring' 'abc', 1,2) " +
								"from Event " +
								"where id = :id", String.class)
						.setParameter( "id", 1L )
						.getSingleResult();
				fail("Should have thrown exception");
			} );
		}
		catch ( Exception e ) {
			assertEquals( QuerySyntaxException.class, e.getCause().getClass() );
		}
	}
