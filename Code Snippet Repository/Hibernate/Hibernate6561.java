	@Test
	@TestForIssue(jiraKey = "")
	public void testSet() throws SQLException {

		// **************
		Session session = openSession();
		session.getTransaction().begin();

		// persist
		EntityEnum entityEnum = new EntityEnum();
		entityEnum.setString( Common.B2 );
		entityEnum.getSet().add( Common.B2 );
		Serializable id = session.save( entityEnum );

		session.getTransaction().commit();
		session.close();
		session = openSession();
		session.getTransaction().begin();

		String sql = "select e from EntityEnum e where :param in elements( e.set ) ";
		Query queryObject = session.createQuery( sql );
		queryObject.setParameter( "param", Common.B2 );

		// ensure EnumType can do #fromName with the trimming
		List<EntityEnum> resultList = queryObject.list();
		assertEquals( resultList.size(), 1 );
		entityEnum = resultList.get( 0 );

		assertEquals( id, entityEnum.getId() );
		assertEquals( Common.B2, entityEnum.getSet().iterator().next() );

		// delete
		assertEquals( 1, session.createSQLQuery( "DELETE FROM set_enum" ).executeUpdate() );
		assertEquals( 1, session.createSQLQuery( "DELETE FROM EntityEnum" ).executeUpdate() );

		session.getTransaction().commit();
		session.close();
	}
