	@Test
	public void testAssociationTableUniqueConstraints() throws Exception {
		Session s = openSession();
		Permission readAccess = new Permission();
		readAccess.setPermission( "read" );
		readAccess.setExpirationDate( new Date() );
		Collection<Permission> coll = new ArrayList<Permission>( 2 );
		coll.add( readAccess );
		coll.add( readAccess );
		Group group = new Group();
		group.setId( new Integer( 1 ) );
		group.setPermissions( coll );
		s.getTransaction().begin();
		try {
			s.persist( group );
			s.getTransaction().commit();
			fail( "Unique constraints not applied on association table" );
		}
		catch (Exception e) {
			//success
			s.getTransaction().rollback();
		}
		finally {
			s.close();
		}
	}
