	@Test
	public void testManyToOne() throws Exception {
		Session session = openSession();
		session.getTransaction().begin();

		Parent parent = new Parent();
		parent.setId( 1L );
		session.persist( parent );

		Child child1 = new Child();
		child1.setId( 1L );
		child1.setParent( parent );
		session.persist( child1 );

		GrandChild grandChild11 = new GrandChild();
		grandChild11.setId( 1L );
		grandChild11.setParent( child1 );
		session.persist( grandChild11 );

		Child child2 = new Child();
		child2.setId( 2L );
		child2.setParent( parent );
		session.persist( child2 );

		GrandChild grandChild21 = new GrandChild();
		grandChild21.setId( 2L );
		grandChild21.setParent( child2 );
		session.persist( grandChild21 );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();

		parent = session.get( Parent.class, 1L );
		session.delete( parent );

		session.getTransaction().commit();
		session.close();
	}
