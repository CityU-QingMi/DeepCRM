	@Before
	public void setup() throws Exception {
		this.connection = mock(Connection.class);
		this.dataSource = mock(DataSource.class);
		this.preparedStatement = mock(PreparedStatement.class);
		this.statement = mock(Statement.class);
		this.resultSet = mock(ResultSet.class);
		this.template = new JdbcTemplate(this.dataSource);
		this.callableStatement = mock(CallableStatement.class);
		given(this.dataSource.getConnection()).willReturn(this.connection);
		given(this.connection.prepareStatement(anyString())).willReturn(this.preparedStatement);
		given(this.preparedStatement.executeQuery()).willReturn(this.resultSet);
		given(this.preparedStatement.executeQuery(anyString())).willReturn(this.resultSet);
		given(this.preparedStatement.getConnection()).willReturn(this.connection);
		given(this.statement.getConnection()).willReturn(this.connection);
		given(this.statement.executeQuery(anyString())).willReturn(this.resultSet);
		given(this.connection.prepareCall(anyString())).willReturn(this.callableStatement);
		given(this.callableStatement.getResultSet()).willReturn(this.resultSet);
	}
