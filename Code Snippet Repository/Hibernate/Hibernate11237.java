	@Test
	public void testDetach() throws Exception {
		CollectionRefIngEntity ing1 = getEntityManager().find( CollectionRefIngEntity.class, ing1_id );
		CollectionRefEdEntity rev1 = getAuditReader().find( CollectionRefEdEntity.class, ed1_id, 1 );

		// First forcing loading of the collection
		assert rev1.getReffering().size() == 1;

		// Now serializing and de-serializing the
		rev1 = serializeDeserialize( rev1 );

		// And checking the colleciton again
		assert rev1.getReffering().contains( ing1 );
		assert rev1.getReffering().size() == 1;

	}
