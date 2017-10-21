	private void doTestNamedParameterUpdate(final boolean namedDeclarations)
			throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE_INT_INT)).willReturn(preparedStatement);

		class NamedParameterUpdater extends SqlUpdate {
			public NamedParameterUpdater() {
				setSql(UPDATE_NAMED_PARAMETERS);
				setDataSource(dataSource);
				if (namedDeclarations) {
					declareParameter(new SqlParameter("priceId", Types.DECIMAL));
					declareParameter(new SqlParameter("perfId", Types.NUMERIC));
				}
				else {
					declareParameter(new SqlParameter(Types.NUMERIC));
					declareParameter(new SqlParameter(Types.DECIMAL));
				}
				compile();
			}

			public int run(int performanceId, int type) {
				Map<String, Integer> params = new HashMap<>();
				params.put("perfId", performanceId);
				params.put("priceId", type);
				return updateByNamedParam(params);
			}
		}

		NamedParameterUpdater pc = new NamedParameterUpdater();
		int rowsAffected = pc.run(1, 1);
		assertEquals(1, rowsAffected);
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.DECIMAL);
	}
