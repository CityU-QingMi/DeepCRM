	@Test(expected = NotAuditedException.class)
	public void testRevisionsCountsForNotAudited() {
		try {
			getAuditReader().getRevisions( NotAuditedSubclassEntity.class, id2_1 );
			assert (false);
		}
		catch (NotAuditedException nae) {
			throw nae;
		}
	}
