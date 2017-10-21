	@Test
	@TestForIssue(jiraKey = "")
	public void testEnumRepresentation() {
		EntityManager entityManager = getEntityManager();
		List<Object> enums1 = entityManager.createNativeQuery(
				"SELECT enums1 FROM EnumSetEntity_enums1_AUD ORDER BY REV ASC"
		).getResultList();
		List<Object> enums2 = entityManager.createNativeQuery(
				"SELECT enums2 FROM EnumSetEntity_enums2_AUD ORDER BY REV ASC"
		).getResultList();
		entityManager.close();

		Assert.assertEquals( Arrays.asList( "X", "Y", "X" ), enums1 );

		Assert.assertEquals( 1, enums2.size() );
		Object enum2 = enums2.get( 0 );
		// Compare the Strings to account for, as an example, Oracle returning a BigDecimal instead of an int.
		Assert.assertEquals( "0", enum2.toString() );
	}
