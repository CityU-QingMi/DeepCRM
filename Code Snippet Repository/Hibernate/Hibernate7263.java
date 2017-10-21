	@Test
	public void testGeneratedIdsWithChildren() {
		
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// set up the record and details
		PurchaseRecord record = new PurchaseRecord();
		Set details = record.getDetails();
		details.add( new PurchaseDetail(record, "p@1", 1) );
		details.add( new PurchaseDetail(record, "p@2", 2) );

		s.persist(record);
		
		t.commit();
		s.close();
		
		// show that the ids were generated (non-zero) and come out the same
		int foundPurchaseNumber = record.getId().getPurchaseNumber();
		String foundPurchaseSequence = record.getId().getPurchaseSequence();
		assertNotNull( record.getId() );
		assertTrue(foundPurchaseNumber > 0);
		assertNotNull(foundPurchaseSequence);
		
		// search on detail1 by itself and show it got the parent's id
		s = openSession();
		t = s.beginTransaction();

		// doAfterTransactionCompletion a find to show that it will wire together fine
		PurchaseRecord foundRecord = (PurchaseRecord) s.get(PurchaseRecord.class,
				new PurchaseRecord.Id(foundPurchaseNumber, foundPurchaseSequence)
				);
		
		t.commit();
		s.close();

		// some simple test to see it fetched
		assertEquals( 2, foundRecord.getDetails().size() );
	}
