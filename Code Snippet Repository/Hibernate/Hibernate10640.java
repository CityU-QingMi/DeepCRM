	@Test
	public void testCurrentStateOfCheckInActivity() {

		final CheckInActivity checkInActivity = getEntityManager().find( CheckInActivity.class, id2 );
		final NormalActivity normalActivity = getEntityManager().find( NormalActivity.class, id3 );

		assertEquals( id2, checkInActivity.getId() );
		assertEquals( 0, checkInActivity.getSequenceNumber().intValue() );
		assertEquals( 30, checkInActivity.getDurationInMinutes().intValue() );
		final Activity relatedActivity = checkInActivity.getRelatedActivity();
		assertEquals( normalActivity.getId(), relatedActivity.getId() );
		assertEquals( normalActivity.getSequenceNumber(), relatedActivity.getSequenceNumber() );
	}
