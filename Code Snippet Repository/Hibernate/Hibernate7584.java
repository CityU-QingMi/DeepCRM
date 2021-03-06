	@Test
	@TestForIssue( jiraKey = "" )
	@RequiresDialectFeature( value = DialectChecks.DoesNotSupportRowValueConstructorSyntax.class )
	public void testGreaterThanOperator() {
		final Session s = openSession();
		try {
			final Query q = s.createQuery( "from Transaction where value > :amount" );
			q.setParameter( "amount", new MonetoryAmount( BigDecimal.ZERO, Currency.getInstance( "EUR" ) ) );
			q.list();
		}
		catch (IllegalArgumentException e) {
			assertTyping( QuerySyntaxException.class, e.getCause() );
			//expected
		}
		finally {
			s.close();
		}
	}
