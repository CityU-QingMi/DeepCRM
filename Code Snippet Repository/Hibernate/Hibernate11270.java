	@Test(expected = NotAuditedException.class)
	public void testHistoryOfNotAudited() {
		try {
			getAuditReader().find( NotAuditedSubclassEntity.class, id2_1, 1 );
			assert (false);
		}
		catch (NotAuditedException nae) {
			throw nae;
		}
	}
