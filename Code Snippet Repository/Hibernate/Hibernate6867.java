	@Test
	public void testJoinColumns() throws Exception {
		Parent parent = new Parent();
		ParentPk pk = new ParentPk();
		pk.firstName = "Bruce";
		pk.lastName = "Willis";
		pk.isMale = true;
		parent.id = pk;
		parent.age = 40;
		Child child = new Child();
		Child child2 = new Child();
		parent.addChild( child );
		parent.addChild( child2 );
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( parent );
		tx.commit();
		s.close();

		assertNotNull( child.id );
		assertNotNull( child2.id );
		assertNotSame( child.id, child2.id );

		s = openSession();
		tx = s.beginTransaction();
		parent = ( Parent ) s.get( Parent.class, pk );
		assertNotNull( parent.children );
		Hibernate.initialize( parent.children );
		assertEquals( 2, parent.children.size() );
		tx.commit();
		s.close();
	}
