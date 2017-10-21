	private EntityMapEnum assertFindCriteria(
			EntityMapEnum expected,
			String mapPath, Object param) {
		assertNotEquals( 0, expected.id );

		Session session = openNewSession();
		session.beginTransaction();
		EntityMapEnum found = (EntityMapEnum) session.createCriteria( EntityMapEnum.class )
				.createCriteria( mapPath, "m" )
				.add( Restrictions.eq( "indices", param ) )
				.uniqueResult();
		//find
		assetEntityMapEnumEquals( expected, found );
		session.getTransaction().commit();
		session.close();
		return found;
	}
