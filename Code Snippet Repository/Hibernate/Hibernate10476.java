	private void checkCorrectlyPersisted(
			Integer expectedId,
			String expectedComp2Str1Rev1, String expectedComp2Str1Rev2) {
		// Verify that the entity was correctly persisted
		EntityManager em = getEntityManager();
		Long entCount = (Long) em.createQuery(
				"select count(s) from DefaultValueComponentTestEntity s where s.id = "
						+ expectedId.toString()
		).getSingleResult();
		Number auditCount = (Number) em.createNativeQuery(
				"select count(id) from DefaultValueComponent_AUD s where s.id = "
						+ expectedId.toString()
		).getSingleResult();
		String comp2Str1Rev1 = (String) em
				.createNativeQuery(
						"select COMP2_STR1 from DefaultValueComponent_AUD s where REV=1 and s.id = "
								+ expectedId.toString()
				).getSingleResult();
		String comp2Str1Rev2 = (String) em
				.createNativeQuery(
						"select COMP2_STR1 from DefaultValueComponent_AUD s where REV=2 and s.id = "
								+ expectedId.toString()
				).getSingleResult();
		assert Long.valueOf( 1L ).equals( entCount );
		assert Integer.valueOf( 2 ).equals( auditCount.intValue() );

		if ( expectedComp2Str1Rev1 == null ) {
			assert comp2Str1Rev1 == null;
		}
		else {
			assert expectedComp2Str1Rev1.equals( comp2Str1Rev1 );
		}

		if ( expectedComp2Str1Rev2 == null ) {
			assert comp2Str1Rev2 == null;
		}
		else {
			assert expectedComp2Str1Rev2.equals( comp2Str1Rev2 );
		}
	}
