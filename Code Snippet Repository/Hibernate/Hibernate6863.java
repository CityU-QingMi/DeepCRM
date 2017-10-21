	@Test
	public void testCascadeDeleteWithUnidirectionalAssociation() throws Exception {
		OnDeleteUnidirectionalOneToManyChild child = new OnDeleteUnidirectionalOneToManyChild();

		doInHibernate( this::sessionFactory, session -> {
			OnDeleteUnidirectionalOneToManyParent parent = new OnDeleteUnidirectionalOneToManyParent();
			parent.children = Collections.singletonList( child);
			session.persist( parent );
		} );

		doInHibernate( this::sessionFactory, session -> {
			session.createQuery("delete from OnDeleteUnidirectionalOneToManyParent").executeUpdate();
		} );

		doInHibernate( this::sessionFactory, session -> {
			OnDeleteUnidirectionalOneToManyChild e1 = session.get( OnDeleteUnidirectionalOneToManyChild.class, child.id );
			assertNull( "delete cascade should work", e1 );
		} );
	}
