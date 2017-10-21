	@Test
	@SuppressWarnings("")
	public void testMapXMLSupport() throws Exception {
		Session s = openSession();
		SessionFactory sf = s.getSessionFactory();
		Transaction tx = s.beginTransaction();

		// Verify that we can persist an object with a couple Map mappings
		VicePresident vpSales = new VicePresident();
		vpSales.name = "Dwight";
		Company company = new Company();
		company.conferenceRoomExtensions.put( "8932", "x1234" );
		company.organization.put( "sales", vpSales );
		s.persist( company );
		s.flush();
		s.clear();

		// For the element-collection, check that the orm.xml entries are honored.
		// This includes: map-key-column/column/collection-table/join-column
		BasicCollectionPersister confRoomMeta = (BasicCollectionPersister) sf.getCollectionMetadata( Company.class.getName() + ".conferenceRoomExtensions" );
		assertEquals( "company_id", confRoomMeta.getKeyColumnNames()[0] );
		assertEquals( "phone_extension", confRoomMeta.getElementColumnNames()[0] );
		assertEquals( "room_number", confRoomMeta.getIndexColumnNames()[0] );
		assertEquals( "phone_extension_lookup", confRoomMeta.getTableName() );
		tx.rollback();
		s.close();
	}
