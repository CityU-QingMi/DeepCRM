	@Test
	public void testDeleteTransient() throws Exception {
		Fee fee = new Fee();
		Fee fee2 = new Fee();
		fee2.setAnotherFee(fee);
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.save(fee);
		s.save(fee2);
		s.flush();
		fee.setCount(123);
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		s.delete(fee);
		s.delete(fee2);
		//foo.setAnotherFee(null);
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		assertTrue( s.createQuery( "from Fee fee" ).list().size()==0 );
		tx.commit();
		s.close();
	}
