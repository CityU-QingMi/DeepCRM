	@Test(expected = ConstraintViolationException.class)
	public void testOneToManyNullCollection() {
		Parent parent = new Parent();
		Child child = new Child();
		child.setParent( parent );

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
