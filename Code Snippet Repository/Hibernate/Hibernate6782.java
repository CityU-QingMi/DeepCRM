	@Test
	public void testAssociationTableAndOrderBy() throws Exception {
		Session s = openSession();
		s.enableFilter( "Groupfilter" );
		Permission readAccess = new Permission();
		readAccess.setPermission( "read" );
		readAccess.setExpirationDate( new Date() );
		Permission writeAccess = new Permission();
		writeAccess.setPermission( "write" );
		writeAccess.setExpirationDate( new Date( new Date().getTime() - 10*60*1000 ) );
		Collection<Permission> coll = new ArrayList<Permission>( 2 );
		coll.add( readAccess );
		coll.add( writeAccess );
		Group group = new Group();
		group.setId( new Integer( 1 ) );
		group.setPermissions( coll );
		s.getTransaction().begin();
		s.persist( group );
		s.flush();
		s.clear();
		group = (Group) s.get( Group.class, group.getId() );
		s.createQuery( "select g from Group g join fetch g.permissions").list();
		assertEquals( "write", group.getPermissions().iterator().next().getPermission() );
		s.getTransaction().rollback();
		s.close();
	}
