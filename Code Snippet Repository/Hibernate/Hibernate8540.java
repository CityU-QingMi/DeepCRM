	@Test
	public void testDeleteUpdatedTransient() throws Exception {
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
		s.update(fee);
		//fee2.setAnotherFee(null);
		s.update(fee2);
		s.delete(fee);
		s.delete(fee2);
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		assertTrue( s.createQuery( "from Fee fee" ).list().size()==0 );
		tx.commit();
		s.close();
	}
