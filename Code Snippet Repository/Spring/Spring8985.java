	@Test
	public void testCreateMappedRecord() throws ResourceException {
		ConnectionFactory connectionFactory = mock(ConnectionFactory.class);
		RecordFactory recordFactory = mock(RecordFactory.class);
		MappedRecord mappedRecord = mock(MappedRecord.class);

		given(connectionFactory.getRecordFactory()).willReturn(recordFactory);
		given(recordFactory.createMappedRecord("name")).willReturn(mappedRecord);

		CciTemplate ct = new CciTemplate(connectionFactory);
		ct.createMappedRecord("name");

		verify(recordFactory).createMappedRecord("name");
	}
