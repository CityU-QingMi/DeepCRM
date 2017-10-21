	@Test
	public void testDelete() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// persist the record
		PurchaseRecord record = new PurchaseRecord();
		s.saveOrUpdate(record);
		
		t.commit();
		s.close();

		PurchaseRecord.Id generatedId = record.getId();
		
		// re-fetch, then delete the record
		s = openSession();
		t = s.beginTransaction();

		PurchaseRecord find = (PurchaseRecord) s.get(PurchaseRecord.class, generatedId);
		s.delete(find);
		assertFalse( s.contains(find) );
		
		t.commit();
		s.close();

		// attempt to re-fetch - show it was deleted
		s = openSession();
		t = s.beginTransaction();

		find = (PurchaseRecord) s.get(PurchaseRecord.class, generatedId);

		t.commit();
		s.close();
		
		assertNull(find);
	}
