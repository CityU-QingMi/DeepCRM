	@Test
	public void testEnumRepresentation() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<Object[]> values = entityManager.createNativeQuery(
				"SELECT enum1, enum2 FROM EnumTypeEntity_AUD ORDER BY REV ASC"
		).getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();

		Assert.assertNotNull( values );
		Assert.assertEquals( 1, values.size() );
		Object[] results = values.get( 0 );
		Assert.assertEquals( 2, results.length );
		Assert.assertEquals( "X", results[0] );
		// Compare the Strings to account for, as an example, Oracle
		// returning a BigDecimal instead of an int.
		Assert.assertEquals( "0", results[1] + "" );
	}
