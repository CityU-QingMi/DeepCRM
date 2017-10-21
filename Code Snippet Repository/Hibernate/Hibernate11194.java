	@Test
	public void testFindRevision() {
		AuditReader vr = getAuditReader();

		ListenerRevEntity rev1Data = vr.findRevision( ListenerRevEntity.class, 1 );
		ListenerRevEntity rev2Data = vr.findRevision( ListenerRevEntity.class, 2 );

		long rev1Timestamp = rev1Data.getTimestamp();
		assert rev1Timestamp > timestamp1;
		assert rev1Timestamp <= timestamp2;

		assert "data1".equals( rev1Data.getData() );

		long rev2Timestamp = rev2Data.getTimestamp();
		assert rev2Timestamp > timestamp2;
		assert rev2Timestamp <= timestamp3;

		assert "data2".equals( rev2Data.getData() );
	}
