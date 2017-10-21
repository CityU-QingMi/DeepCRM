	@Test
	public void testSaveOrUpdate() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Date timestamp1 = new Date();
		Date timestamp2 = new Date(timestamp1.getTime() + 1);

		// persist the record
		PurchaseRecord record = new PurchaseRecord();
		record.setTimestamp(timestamp1);
		s.saveOrUpdate(record);
		
		t.commit();
		s.close();

		// test that the id was generated
		PurchaseRecord.Id generatedId = record.getId();
		assertNotNull(generatedId);
		assertNotNull( generatedId.getPurchaseSequence() );
		
		// change the timestamp
		record.setTimestamp(timestamp2);
		
		s = openSession();
		t = s.beginTransaction();

		s.saveOrUpdate(record);
		
		t.commit();
		s.close();

		// see that we get the *same* id, and the new timestamp
		assertSame( generatedId, record.getId() );
		assertEquals( df.format(timestamp2), df.format(record.getTimestamp()) );
	}
