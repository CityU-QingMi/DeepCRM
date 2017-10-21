	@Test
	public void testAssociationOverride() {
		// class level association overrides
		assertForeignKey( "FK_COMPANY_OWNER", "OWNER_PERSON_ID" );
		assertForeignKey( "FK_COMPANY_CREDIT_CARD", "CREDIT_CARD_ID" );
		assertForeignKey( "FK_COMPANY_CREDIT_CARD3", "CREDIT_CARD_ID3" );
		assertNoForeignKey( "FK_COMPANY_OWNER2", "OWNER_PERSON_ID2" );
		assertNoForeignKey( "FK_COMPANY_CREDIT_CARD2", "CREDIT_CARD_ID2" );
		assertNoForeignKey( "FK_COMPANY_CREDIT_CARD4", "CREDIT_CARD_ID4" );

		// embeddable association overrides
		assertForeignKey( "FK_COMPANY_CARD", "AO_CI_CC_ID" );
		assertNoForeignKey( "FK_COMPANY_CARD2", "AO_CI_CC_ID2" );
		assertForeignKey( "FK_COMPANY_CARD3", "AO_CI_CC_ID3" );
		assertNoForeignKey( "FK_COMPANY_CARD4", "AO_CI_CC_ID4" );
	}
