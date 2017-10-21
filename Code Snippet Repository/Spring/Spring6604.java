	@Test
	public void testUpdateAndGeneratedKeys() throws SQLException {
		given(resultSetMetaData.getColumnCount()).willReturn(1);
		given(resultSetMetaData.getColumnLabel(1)).willReturn("1");
		given(resultSet.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getObject(1)).willReturn(11);
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(preparedStatement.getGeneratedKeys()).willReturn(resultSet);
		given(connection.prepareStatement(INSERT_GENERATE_KEYS,
				PreparedStatement.RETURN_GENERATED_KEYS)
			).willReturn(preparedStatement);

		GeneratedKeysUpdater pc = new GeneratedKeysUpdater();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		int rowsAffected = pc.run("rod", generatedKeyHolder);

		assertEquals(1, rowsAffected);
		assertEquals(1, generatedKeyHolder.getKeyList().size());
		assertEquals(11, generatedKeyHolder.getKey().intValue());
		verify(preparedStatement).setString(1, "rod");
		verify(resultSet).close();
	}
