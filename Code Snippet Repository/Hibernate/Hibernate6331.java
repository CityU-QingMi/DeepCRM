	@Test
	public void testOneToManyNotNullCollection() {
		Parent parent = new Parent();
		parent.id = 1L;
		Child child = new Child();
		child.id = 1L;

		List<Child> children = new ArrayList<Child>();
		children.add( child );

		child.setParent( parent );
		parent.setChildren( children );

		Session s = openSession();
		Transaction t = s.beginTransaction();
		parent = (Parent) s.merge( parent );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete( parent );
		t.commit();
		s.close();
	}
