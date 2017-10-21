	@Test
	public void testFindRevision() {
		AuditReader vr = getAuditReader();

		long rev1Timestamp = vr.findRevision( CustomBoxedRevEntity.class, 1 ).getCustomTimestamp();
		assert rev1Timestamp > timestamp1;
		assert rev1Timestamp <= timestamp2;

		long rev2Timestamp = vr.findRevision( CustomBoxedRevEntity.class, 2 ).getCustomTimestamp();
		assert rev2Timestamp > timestamp2;
		assert rev2Timestamp <= timestamp3;
	}
