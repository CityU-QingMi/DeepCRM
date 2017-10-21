	@Test
	public void testAssociationTableAndOrderByWithSet() throws Exception {
		Session s = openSession();
		s.enableFilter( "Groupfilter" );

		Permission readAccess = new Permission();
		readAccess.setPermission( "read" );
		readAccess.setExpirationDate( new Date() );
		
		Permission writeAccess = new Permission();
		writeAccess.setPermission( "write" );
		writeAccess.setExpirationDate( new Date( new Date().getTime() - 10*60*1000 ) );
		
		Permission executeAccess = new Permission();
		executeAccess.setPermission( "execute" );
		executeAccess.setExpirationDate( new Date( new Date().getTime() - 5*60*1000 ) );
		
		Set<Permission> coll = new HashSet<Permission>( 3 );
		coll.add( readAccess );
		coll.add( writeAccess );
		coll.add( executeAccess );

		GroupWithSet group = new GroupWithSet();
		group.setId( new Integer( 1 ) );
		group.setPermissions( coll );
		s.getTransaction().begin();
		s.persist( group );
		s.flush();
		s.clear();

		group = (GroupWithSet) s.get( GroupWithSet.class, group.getId() );
		s.createQuery( "select g from Group g join fetch g.permissions").list();
		Iterator<Permission> permIter = group.getPermissions().iterator();
		assertEquals( "write", permIter.next().getPermission() );
		assertEquals( "execute", permIter.next().getPermission() );
		assertEquals( "read", permIter.next().getPermission() );
		s.getTransaction().rollback();
		s.close();
	}
