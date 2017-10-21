	private void doWork(final Session s) {
		s.doWork(
				new AbstractWork() {
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement( "delete from t_user" );
						ps.execute();
					}
				}
		);
	}
