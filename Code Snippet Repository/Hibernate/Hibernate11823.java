	@Test
	public void testNativeEnvers() throws Exception {
		final ServiceReference sr = bundleContext.getServiceReference( SessionFactory.class.getName() );
		final SessionFactory sf = ( SessionFactory )bundleContext.getService( sr );

		final Integer adpId;

		Session s = sf.openSession();
		s.getTransaction().begin();
		AuditedDataPoint adp = new AuditedDataPoint( "Chris" );
		s.persist( adp );
		s.getTransaction().commit();
		adpId = adp.getId();
		s.close();

		s = sf.openSession();
		s.getTransaction().begin();
		adp = s.get( AuditedDataPoint.class, adpId );
		adp.setName( "Chris2" );
		s.getTransaction().commit();
		s.close();

		s = sf.openSession();
		AuditReader ar = AuditReaderFactory.get( s );
		assertEquals( 2, ar.getRevisions( AuditedDataPoint.class, adpId ).size() );
		AuditedDataPoint rev1 = ar.find( AuditedDataPoint.class, adpId, 1 );
		AuditedDataPoint rev2 = ar.find( AuditedDataPoint.class, adpId, 2 );
		assertEquals( new AuditedDataPoint( adpId, "Chris" ), rev1 );
		assertEquals( new AuditedDataPoint( adpId, "Chris2" ), rev2 );
		s.close();
	}
