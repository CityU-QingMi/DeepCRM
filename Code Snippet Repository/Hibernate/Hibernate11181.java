	@Test
	public void testFindRevision() {
		AuditReader vr = getAuditReader();

		long rev1Timestamp = vr.findRevision( CustomDateRevEntity.class, 1 ).getDateTimestamp().getTime();
		assert rev1Timestamp > timestamp1;
		assert rev1Timestamp <= timestamp2;

		long rev2Timestamp = vr.findRevision( CustomDateRevEntity.class, 2 ).getDateTimestamp().getTime();
		assert rev2Timestamp > timestamp2;
		assert rev2Timestamp <= timestamp3;
	}
