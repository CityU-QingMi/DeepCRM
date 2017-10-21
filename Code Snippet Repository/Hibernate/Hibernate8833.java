	@Test
	public void testNamingComponentPath() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final MetadataSources ms = new MetadataSources( ssr )
				.addAnnotatedClass( Employee.class )
				.addAnnotatedClass( BankAccounts.class )
				.addAnnotatedClass( BankAccount.class )
				.addAnnotatedClass( WebUser.class );

			final Metadata metadata = ms.getMetadataBuilder()
					.applyImplicitNamingStrategy(
						ImplicitNamingStrategyComponentPathImpl.INSTANCE )
					.build();

			checkDefaultJoinTableAndAllColumnNames(
					metadata,
					Employee.class,
					"bankAccounts.accounts",
					"ComponentNamingStrategyForJoinColumnTest$Employee_bankAccounts_accounts",
					"ComponentNamingStrategyForJoinColumnTest$Employee_id",
					new String[] {
						"ComponentNamingStrategyForJoinColumnTest$Employee_id",
						"bankAccounts_accounts_accountNumber",
						"bankAccounts_accounts_bankName",
						"bankAccounts_accounts_verificationUser_id"
					}
			);
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
