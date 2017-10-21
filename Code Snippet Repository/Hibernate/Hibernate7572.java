	@Test
	public void testIlikeMimicing() {
		SessionFactory sf = new Configuration()
				.addAnnotatedClass( IrrelevantEntity.class )
				.setProperty( AvailableSettings.DIALECT, NonIlikeSupportingDialect.class.getName() )
				.setProperty( Environment.HBM2DDL_AUTO, "create-drop" )
				.buildSessionFactory();
		try {
			final Criteria criteria = sf.openSession().createCriteria( IrrelevantEntity.class );
			final CriteriaQueryTranslator translator = new CriteriaQueryTranslator(
					(SessionFactoryImplementor) sf,
					(CriteriaImpl) criteria,
					IrrelevantEntity.class.getName(),
					"a"
			);
			final Criterion ilikeExpression = Restrictions.ilike( "name", "abc" );
			final String ilikeExpressionSqlFragment = ilikeExpression.toSqlString( criteria, translator );
			assertEquals( "lowLowLow(a.name) like ?", ilikeExpressionSqlFragment );
		}
		finally {
			sf.close();
		}
	}
