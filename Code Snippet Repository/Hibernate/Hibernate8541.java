	@Test
	public void testUpdateOrder() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Fee fee1 = new Fee();
		s.save(fee1);
		Fee fee2 = new Fee();
		fee1.setFee(fee2);
		fee2.setFee(fee1);
		fee2.setFees( new HashSet() );
		Fee fee3 = new Fee();
		fee3.setFee(fee1);
		fee3.setAnotherFee(fee2);
		fee2.setAnotherFee(fee3);
		s.save(fee3);
		s.save(fee2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		fee1.setCount(10);
		fee2.setCount(20);
		fee3.setCount(30);
		s.update(fee1);
		s.update(fee2);
		s.update(fee3);
		s.flush();
		s.delete(fee1);
		s.delete(fee2);
		s.delete(fee3);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		assertTrue( s.createQuery( "from Fee fee" ).list().size()==0 );
		s.getTransaction().commit();
		s.close();
	}
