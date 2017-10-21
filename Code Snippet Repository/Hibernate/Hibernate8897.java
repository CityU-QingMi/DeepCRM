	@Test
	public void testManyToOne() throws Exception {
		Session session = openSession();
		session.getTransaction().begin();

		Parent parent = new Parent();
		parent.id = 1L;
		session.persist( parent );

		Child child1 = new Child();
		child1.id = 1L;
		child1.parent = parent;
		session.persist( child1 );

		GrandChild grandChild11 = new GrandChild();
		grandChild11.id = 1L;
		grandChild11.parent = child1;
		session.persist( grandChild11 );

		Child child2 = new Child();
		child2.id = 2L;
		child2.parent = parent;
		session.persist( child2 );

		GrandChild grandChild21 = new GrandChild();
		grandChild21.id = 2L;
		grandChild21.parent = child2;
		session.persist( grandChild21 );

		GrandChild grandChild22 = new GrandChild();
		grandChild22.id = 3L;
		grandChild22.parent = child2;
		session.persist( grandChild22 );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.getTransaction().begin();

		parent = session.get( Parent.class, 1L );
		session.delete( parent );

		session.getTransaction().commit();
		session.close();
	}
