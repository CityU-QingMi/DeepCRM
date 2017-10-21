	@Test
	public void testLoad() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// persist the record, then get the id and timestamp back
		PurchaseRecord record = new PurchaseRecord();
		s.persist(record);
		
		t.commit();
		s.close();

		PurchaseRecord.Id id = record.getId();
		Date timestamp = record.getTimestamp();
		
		// using the given id, load a transient record
		PurchaseRecord toLoad = new PurchaseRecord();
		
		s = openSession();
		t = s.beginTransaction();

		s.load(toLoad, id);
		
		t.commit();
		s.close();
		
		// show that the correct timestamp and ids were loaded
		assertEquals( id, toLoad.getId() );
		assertEquals( df.format(timestamp), df.format(toLoad.getTimestamp()) );
	}
