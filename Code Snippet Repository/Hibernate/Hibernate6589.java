	@Test
	public void testIqFilter(){
		openSession();
		session.beginTransaction();
		
		assertCount(3);	
		session.enableFilter("iqRange").setParameter("min", 101).setParameter("max", 140);
		assertCount(1);	
		
		session.getTransaction().commit();
		session.close();
	}
