	@Test
	public void testCreateIndexedRecord() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		IndexedRecord indexedRecord = mock(IndexedRecord.class);
		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(recordFactory.createIndexedRecord("name")).willReturn(indexedRecord);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.createIndexedRecord("name");

		verify(recordFactory).createIndexedRecord("name");
	}
