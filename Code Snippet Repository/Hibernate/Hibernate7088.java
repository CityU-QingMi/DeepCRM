	@Test
	public void testXmlDefaultOverriding() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Manufacturer manufacturer = new Manufacturer();
		s.persist( manufacturer );
		s.flush();
		s.clear();

		assertEquals( 1, s.getNamedQuery( "manufacturer.findAll" ).list().size() );
		tx.rollback();
		s.close();
	}
