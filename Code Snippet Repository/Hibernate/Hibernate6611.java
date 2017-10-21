	@Test
	public void testTableGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Ball b = new Ball();
		Dog d = new Dog();
		Computer c = new Computer();
		s.persist( b );
		s.persist( d );
		s.persist(c);
		tx.commit();
		s.close();
		assertEquals("table id not generated", new Integer(1), b.getId());
		assertEquals("generator should not be shared", new Integer(1), d
				.getId());
		assertEquals("default value should work", new Long(1), c.getId());

		s = openSession();
		tx = s.beginTransaction();
		s.delete(s.get(Ball.class, new Integer(1)));
		s.delete(s.get(Dog.class, new Integer(1)));
		s.delete(s.get(Computer.class, new Long(1)));
		tx.commit();
		s.close();
	}
