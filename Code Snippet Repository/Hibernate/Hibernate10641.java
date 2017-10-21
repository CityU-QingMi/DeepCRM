	@Test
	public void testCheckCurrentStateOfNormalActivities() throws Exception {
		final NormalActivity normalActivity1 = getEntityManager().find( NormalActivity.class, id1 );
		final NormalActivity normalActivity2 = getEntityManager().find( NormalActivity.class, id3 );

		assertEquals( id1, normalActivity1.getId() );
		assertEquals( 1, normalActivity1.getSequenceNumber().intValue() );
		assertEquals( id3, normalActivity2.getId() );
		assertEquals( 2, normalActivity2.getSequenceNumber().intValue() );
	}
