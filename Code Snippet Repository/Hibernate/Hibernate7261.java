	@Test
	public void testMerge() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Date timestamp1 = new Date();
		Date timestamp2 = new Date(timestamp1.getTime() + 1);

		// persist the record
		PurchaseRecord record = new PurchaseRecord();
		s.persist(record);
		
		t.commit();
		s.close();

		// test that the id was generated
		PurchaseRecord.Id generatedId = record.getId();
		assertNotNull(generatedId);
		assertNotNull( generatedId.getPurchaseSequence() );
		
		s = openSession();
		t = s.beginTransaction();

		// update detached object, retrieve persistent object, then merge
		PurchaseRecord detached = record;
		detached.setTimestamp(timestamp2);
		PurchaseRecord persistent = (PurchaseRecord) s.get(PurchaseRecord.class, generatedId);
		
		// show that the timestamp hasn't changed
		assertEquals( df.format(timestamp1), df.format(persistent.getTimestamp()) );
		
		s.merge(detached);
		
		t.commit();
		s.close();

		// show that the persistent object was changed only after the session flush
		assertEquals( timestamp2, persistent.getTimestamp() );
		
		// show that the persistent store was updated - not just the in-memory object
		s = openSession();
		t = s.beginTransaction();

		persistent = (PurchaseRecord) s.get(PurchaseRecord.class, generatedId);

		t.commit();
		s.close();
		
		assertEquals( df.format(timestamp2), df.format(persistent.getTimestamp()) );
	}
