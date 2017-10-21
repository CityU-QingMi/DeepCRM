	@Test
	public void testNoCascadeCache() {
		Parent parent = new Parent();

		BaseChild firstChild = new BaseChild();
		parent.getChildren().add( firstChild );
		
		Session s = openSession();
		s.beginTransaction();
		s.save(parent);
		s.getTransaction().commit();
		s.clear();

		Child secondChild = new Child();
		secondChild.setName( "SecondChildName" );
		parent.getChildren().add( secondChild );

		firstChild.setDependency( secondChild );

		s.beginTransaction();
		Parent mergedParent = (Parent) s.merge( parent );
		s.getTransaction().commit();
		s.close();
		
		assertNotNull( mergedParent );
		assertEquals( mergedParent.getChildren().size(), 2 );
	}
