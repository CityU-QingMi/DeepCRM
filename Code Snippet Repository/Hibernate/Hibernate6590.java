	@Test
	public void testPregnantFilter(){
		openSession();
		session.beginTransaction();
		
		assertCount(3);	
		session.enableFilter("pregnantOnly");
		assertCount(1);	
		
		session.getTransaction().commit();
		session.close();
	}
