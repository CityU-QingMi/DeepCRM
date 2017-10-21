	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( Contact.class, pc_id ) );
		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( PersonalContact.class, pc_id ) );

		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( Address.class, a1_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( Address.class, a1_id ) );

		assert Arrays.asList( 2 ).equals( getAuditReader().getRevisions( Address.class, a2_id ) );
		assert Arrays.asList( 2 ).equals( getAuditReader().getRevisions( Address.class, a2_id ) );
	}
