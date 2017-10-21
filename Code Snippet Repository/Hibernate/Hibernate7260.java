	@Test
	public void testEvict() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Date timestamp1 = new Date();
		Date timestamp2 = new Date(timestamp1.getTime() + 1);
		
		// persist the record, then evict it, then make changes to it ("within" the session)
		PurchaseRecord record = new PurchaseRecord();
		record.setTimestamp(timestamp1);
		s.persist(record);
		s.flush();
		s.evict(record);
		
		record.setTimestamp(timestamp2);
		
		t.commit();
		s.close();

		PurchaseRecord.Id generatedId = record.getId();
		
		// now, re-fetch the record and show that the timestamp change wasn't persisted
		s = openSession();
		t = s.beginTransaction();

		PurchaseRecord persistent = (PurchaseRecord) s.get(PurchaseRecord.class, generatedId);
		
		t.commit();
		s.close();
		
		assertEquals( generatedId, persistent.getId() );
		assertEquals( df.format(timestamp1), df.format(persistent.getTimestamp()) );
	}
