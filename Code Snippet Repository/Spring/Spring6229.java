	@Override
	public final Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
		LobCreator lobCreator = this.lobHandler.getLobCreator();
		try {
			setValues(ps, lobCreator);
			return ps.executeUpdate();
		}
		finally {
			lobCreator.close();
		}
	}
