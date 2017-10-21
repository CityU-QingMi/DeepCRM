	@Test
	public void testHistoryOfContact() {
		assert getAuditReader().find( Contact.class, pc_id, 1 ).getAddresses().equals(
				TestTools.makeSet( new Address( a1_id, "a1" ) )
		);

		assert getAuditReader().find( Contact.class, pc_id, 2 ).getAddresses().equals(
				TestTools.makeSet( new Address( a1_id, "a1" ), new Address( a2_id, "a2" ) )
		);
	}
