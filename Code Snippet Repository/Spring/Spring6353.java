		@Override
		public void setValue(PreparedStatement ps, int paramIndex) throws SQLException {
			this.xmlObject = ps.getConnection().createSQLXML();
			try {
				provideXml(this.xmlObject);
			}
			catch (IOException ex) {
				throw new DataAccessResourceFailureException("Failure encountered while providing XML", ex);
			}
			ps.setSQLXML(paramIndex, this.xmlObject);
		}
