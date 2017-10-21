	@Test
	public void doTestFirstRevisionOfCheckInActivity() throws Exception {
		CheckInActivity checkInActivity = getAuditReader().find( CheckInActivity.class, id2, 2 );
		NormalActivity normalActivity = getAuditReader().find( NormalActivity.class, id1, 2 );

		assertEquals( id2, checkInActivity.getId() );
		assertEquals( 0, checkInActivity.getSequenceNumber().intValue() );
		assertEquals( 30, checkInActivity.getDurationInMinutes().intValue() );
		Activity relatedActivity = checkInActivity.getRelatedActivity();
		assertEquals( normalActivity.getId(), relatedActivity.getId() );
		assertEquals( normalActivity.getSequenceNumber(), relatedActivity.getSequenceNumber() );
	}
