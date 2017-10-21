	@Test
	public void testValid() throws Exception {
		String sql = "SELECT NAME AS NAME, PROPERTY AS PROPERTY, VALUE AS VALUE FROM T";

		Connection connection = mock(Connection.class);
		DataSource dataSource = mock(DataSource.class);
		given(dataSource.getConnection()).willReturn(connection);

		ResultSet resultSet = mock(ResultSet.class);
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getString(1)).willReturn("one", "one");
		given(resultSet.getString(2)).willReturn("(class)", "age");
		given(resultSet.getString(3)).willReturn("org.springframework.tests.sample.beans.TestBean", "53");

		Statement statement = mock(Statement.class);
		given(statement.executeQuery(sql)).willReturn(resultSet);
		given(connection.createStatement()).willReturn(statement);

		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		JdbcBeanDefinitionReader reader = new JdbcBeanDefinitionReader(bf);
		reader.setDataSource(dataSource);
		reader.loadBeanDefinitions(sql);
		assertEquals("Incorrect number of bean definitions", 1, bf.getBeanDefinitionCount());
		TestBean tb = (TestBean) bf.getBean("one");
		assertEquals("Age in TestBean was wrong.", 53, tb.getAge());

		verify(resultSet).close();
		verify(statement).close();
	}
