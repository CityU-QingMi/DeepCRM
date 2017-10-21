	@Test
	public void testArithmetic() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Zoo zoo = new Zoo();
		zoo.setName("Melbourne Zoo");
		s.persist(zoo);
		s.createQuery("select 2*2*2*2*(2*2) from Zoo").uniqueResult();
		s.createQuery("select 2 / (1+1) from Zoo").uniqueResult();
		int result0 = ( (Integer) s.createQuery("select 2 - (1+1) from Zoo").uniqueResult() ).intValue();
		int result1 = ( (Integer) s.createQuery("select 2 - 1 + 1 from Zoo").uniqueResult() ).intValue();
		int result2 = ( (Integer) s.createQuery("select 2 * (1-1) from Zoo").uniqueResult() ).intValue();
		int result3 = ( (Integer) s.createQuery("select 4 / (2 * 2) from Zoo").uniqueResult() ).intValue();
		int result4 = ( (Integer) s.createQuery("select 4 / 2 * 2 from Zoo").uniqueResult() ).intValue();
		int result5 = ( (Integer) s.createQuery("select 2 * (2/2) from Zoo").uniqueResult() ).intValue();
		int result6 = ( (Integer) s.createQuery("select 2 * (2/2+1) from Zoo").uniqueResult() ).intValue();
		assertEquals(result0, 0);
		assertEquals(result1, 2);
		assertEquals(result2, 0);
		assertEquals(result3, 1);
		assertEquals(result4, 4);
		assertEquals(result5, 2);
		assertEquals(result6, 4);
		s.delete(zoo);
		t.commit();
		s.close();
	}
