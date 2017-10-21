	@Test
	public void doTestSecondRevisionOfCheckInActivity() throws Exception {
		CheckInActivity checkInActivity = getAuditReader().find( CheckInActivity.class, id2, 4 );
		NormalActivity normalActivity = getAuditReader().find( NormalActivity.class, id3, 4 );

		assertEquals( id2, checkInActivity.getId() );
		assertEquals( 0, checkInActivity.getSequenceNumber().intValue() );
		assertEquals( 30, checkInActivity.getDurationInMinutes().intValue() );
		Activity relatedActivity = checkInActivity.getRelatedActivity();
		assertEquals( normalActivity.getId(), relatedActivity.getId() );
		assertEquals( normalActivity.getSequenceNumber(), relatedActivity.getSequenceNumber() );
	}
