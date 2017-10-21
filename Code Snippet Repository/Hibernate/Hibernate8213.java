	private void insertNewRow(Session session) {
		final SessionImplementor si = (SessionImplementor) session;
		final SessionFactoryImplementor sfi = si.getFactory();

		session.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement statement = null;
						try {
							statement = connection.prepareStatement( "INSERT INTO sequenceIdentifier VALUES (?)" );
							statement.setObject( 1, sfi.getIdentifierGenerator( SequenceIdentifier.class.getName() ).generate( si, null ) );
							statement.executeUpdate();
						}
						finally {
							if ( statement != null ) {
								statement.close();
							}
						}
					}
				}
		);
	}
