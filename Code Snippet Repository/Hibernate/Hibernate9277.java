	@Test
	public void testLegacySettingSelection() {
		final TransactionCoordinatorBuilderInitiator initiator = new TransactionCoordinatorBuilderInitiator();

		TransactionCoordinatorBuilder builder = initiator.initiateService(
				Collections.singletonMap(
						TransactionCoordinatorBuilderInitiator.LEGACY_SETTING_NAME,
						"org.hibernate.transaction.JDBCTransactionFactory"
				),
				bsr
		);
		assertThat( builder, instanceOf( JdbcResourceLocalTransactionCoordinatorBuilderImpl.class ) );

		builder = initiator.initiateService(
				Collections.singletonMap(
						TransactionCoordinatorBuilderInitiator.LEGACY_SETTING_NAME,
						"org.hibernate.transaction.JTATransactionFactory"
				),
				bsr
		);
		assertThat( builder, instanceOf( JtaTransactionCoordinatorBuilderImpl.class ) );

		builder = initiator.initiateService(
				Collections.singletonMap(
						TransactionCoordinatorBuilderInitiator.LEGACY_SETTING_NAME,
						"org.hibernate.transaction.CMTTransactionFactory"
				),
				bsr
		);
		assertThat( builder, instanceOf( JtaTransactionCoordinatorBuilderImpl.class ) );
	}
