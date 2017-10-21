	@Test
	public void shouldPersistDebtorAccountWhenParentServiceAgreementPersisted() {
		Session s = openSession();
		s.beginTransaction();
		s.save( new Sub() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Sub" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
