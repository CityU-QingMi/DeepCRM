	@Test(expected = ConstraintViolationException.class)
	public void testOneToManyNullCollection() {
		Parent parent = new Parent();
		parent.id = 1L;

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
