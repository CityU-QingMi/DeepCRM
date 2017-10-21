	private EntityMapEnum assertFindEntityMapEnum(
			EntityMapEnum expected,
			String query,
			String queryWithParam, Object param,
			String nativeQueryCheck) {

		assertNotEquals( 0, expected.id );
		assertNotNull( param );

		Session session = openNewSession();
		EntityMapEnum found = (EntityMapEnum) session.createQuery( query ).uniqueResult();

		//find
		assetEntityMapEnumEquals( expected, found );

		// find with parameter
		found = (EntityMapEnum) session.createQuery( queryWithParam )
				.setParameter( "param", param ).uniqueResult();
		assetEntityMapEnumEquals( expected, found );

		//native query check
		SQLQuery sqlQuery = session.createSQLQuery( nativeQueryCheck );
		sqlQuery.setParameter( "idEntityMapEnum", expected.id );
		Object o = sqlQuery.uniqueResult();
		assertNotNull( o );

		return found;
	}
