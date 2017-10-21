	@Before
	public void setUp() throws Exception {
		this.connection = mock(Connection.class);
		this.dataSource = mock(DataSource.class);
		this.statement = mock(Statement.class);
		this.preparedStatement = mock(PreparedStatement.class);
		this.resultSet = mock(ResultSet.class);
		this.resultSetMetaData = mock(ResultSetMetaData.class);
		this.template = new JdbcTemplate(this.dataSource);
		given(this.dataSource.getConnection()).willReturn(this.connection);
		given(this.resultSet.getMetaData()).willReturn(this.resultSetMetaData);
		given(this.resultSetMetaData.getColumnCount()).willReturn(1);
		given(this.resultSetMetaData.getColumnLabel(1)).willReturn("age");
		given(this.connection.createStatement()).willReturn(this.statement);
		given(this.connection.prepareStatement(anyString())).willReturn(this.preparedStatement);
		given(this.preparedStatement.executeQuery()).willReturn(this.resultSet);
		given(this.statement.executeQuery(anyString())).willReturn(this.resultSet);
	}
