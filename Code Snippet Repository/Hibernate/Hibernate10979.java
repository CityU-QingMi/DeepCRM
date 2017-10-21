	@Test
	public void testOneToManyAssociationAuditQuery() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final AuditReader auditReader = getAuditReader();
			final Contract contract = auditReader.find( Contract.class, 1, 1 );
			final Design design = auditReader.find( Design.class, 1, 1 );
			assertEquals( 1, contract.getDesigns().size() );

			final DesignContract designContract = contract.getDesigns().iterator().next();
			assertEquals( contract.getId(), designContract.getContract().getId() );
			assertEquals( design.getId(), designContract.getDesign().getId() );
		} );
	}
