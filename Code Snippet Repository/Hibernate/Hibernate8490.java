	public void testCollectionCUD() throws HibernateException, SQLException {
		Role role = new Role();
		role.setName("Jim Flanders");
		Intervention iv = new Medication();
		iv.setDescription("JF medical intervention");
		role.getInterventions().add(iv);

		List sx = new ArrayList();
		sx.add("somewhere");
		sx.add("somehow");
		sx.add("whatever");
		role.setBunchOfStrings(sx);

		Session s = openSession();
		s.beginTransaction();
		s.save(role);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Role r = (Role) s.get(Role.class, Long.valueOf(role.getId()));
		assertNotSame(role,r);
		assertEquals(1,r.getInterventions().size());
		assertEquals(3, r.getBunchOfStrings().size());
		r.getBunchOfStrings().set(1, "replacement");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		r = (Role) s.get(Role.class,new Long(role.getId()));
		assertNotSame(role,r);

		assertEquals(r.getBunchOfStrings().get(1),"replacement");
		assertEquals(3, r.getBunchOfStrings().size());

		r.getBunchOfStrings().set(1, "replacement");

		r.getBunchOfStrings().remove(1);
		s.flush();

		r.getBunchOfStrings().clear();
		s.getTransaction().commit();
		s.close();
	}
