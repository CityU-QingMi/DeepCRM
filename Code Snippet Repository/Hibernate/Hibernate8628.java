	@Test
	public void testMultiTableNativeId() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Multi multi = new Multi();
		multi.setExtraProp("extra");
		Long id = (Long) s.save(multi);
		assertTrue( id!=null );
		s.delete(multi);
		t.commit();
		s.close();
	}
