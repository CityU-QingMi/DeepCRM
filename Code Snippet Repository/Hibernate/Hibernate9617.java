	@Test
	public void testSave() throws Exception {
		deleteData();

		Session s = openSession();
		s.beginTransaction();
		Widget obj = new Widget();
		obj.setValueThree(5);
		final Integer id = (Integer) s.save(obj);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		doWork(id, s);

		s.getTransaction().commit();
		s.close();

		deleteData();
	}
