	@Test
	public void testAddInvoices() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("org/springframework/jdbc/object/GenericStoredProcedureTests-context.xml"));
		Connection connection = mock(Connection.class);
		DataSource dataSource = mock(DataSource.class);
		given(dataSource.getConnection()).willReturn(connection);
		CallableStatement callableStatement = mock(CallableStatement.class);
		TestDataSourceWrapper testDataSource = (TestDataSourceWrapper) bf.getBean("dataSource");
		testDataSource.setTarget(dataSource);

		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(3)).willReturn(4);

		given(connection.prepareCall("{call " + "add_invoice" + "(?, ?, ?)}")).willReturn(callableStatement);

		StoredProcedure adder = (StoredProcedure) bf.getBean("genericProcedure");
		Map<String, Object> in = new HashMap<>(2);
		in.put("amount", 1106);
		in.put("custid", 3);
		Map<String, Object> out = adder.execute(in);
		Integer id = (Integer) out.get("newid");
		assertEquals(4, id.intValue());

		verify(callableStatement).setObject(1, 1106, Types.INTEGER);
		verify(callableStatement).setObject(2, 3, Types.INTEGER);
		verify(callableStatement).registerOutParameter(3, Types.INTEGER);
		verify(callableStatement).close();
	}
