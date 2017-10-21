	private List getResults(final EntityPersister entityPersister, final Callback callback) {
		final LoadPlan plan = Helper.INSTANCE.buildLoadPlan( sessionFactory(), entityPersister );

		final LoadQueryDetails queryDetails = Helper.INSTANCE.buildLoadQueryDetails( plan, sessionFactory() );
		final String sql = queryDetails.getSqlStatement();
		final ResultSetProcessor resultSetProcessor = queryDetails.getResultSetProcessor();

		final List results = new ArrayList();

		final Session workSession = openSession();
		workSession.beginTransaction();
		workSession.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						System.out.println( "SQL : " + sql );
						PreparedStatement ps = connection.prepareStatement( sql );
						callback.bind( ps );
						ResultSet resultSet = ps.executeQuery();
						//callback.beforeExtractResults( workSession );
						results.addAll(
								resultSetProcessor.extractResults(
										resultSet,
										(SessionImplementor) workSession,
										callback.getQueryParameters(),
										new NamedParameterContext() {
											@Override
											public int[] getNamedParameterLocations(String name) {
												return new int[0];
											}
										},
										true,
										false,
										null,
										null
								)
						);
						resultSet.close();
						ps.close();
					}
				}
		);
		workSession.getTransaction().commit();
		workSession.close();

		return results;
	}
