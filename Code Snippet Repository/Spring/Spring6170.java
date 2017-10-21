	@Override
	public final void processRow(ResultSet rs) throws SQLException {
		if (this.rowCount == 0) {
			ResultSetMetaData rsmd = rs.getMetaData();
			this.columnCount = rsmd.getColumnCount();
			this.columnTypes = new int[this.columnCount];
			this.columnNames = new String[this.columnCount];
			for (int i = 0; i < this.columnCount; i++) {
				this.columnTypes[i] = rsmd.getColumnType(i + 1);
				this.columnNames[i] = JdbcUtils.lookupColumnName(rsmd, i + 1);
			}
			// could also get column names
		}
		processRow(rs, this.rowCount++);
	}
