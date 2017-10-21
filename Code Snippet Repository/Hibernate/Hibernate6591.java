	@Test
	public void testNonHumanFilter(){
		openSession();
		session.beginTransaction();
		
		assertCount(3);	
		session.enableFilter("ignoreSome").setParameter("name", "Homo Sapiens");
		assertCount(0);	
		
		session.getTransaction().commit();
		session.close();
	}
