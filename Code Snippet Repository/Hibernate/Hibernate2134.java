		@Override
		public String resolveSchemaName(Connection connection, Dialect dialect) throws SQLException {
			final String command = dialect.getCurrentSchemaCommand();
			if ( command == null ) {
				throw new HibernateException(
						"Use of DefaultSchemaNameResolver requires Dialect to provide the " +
								"proper SQL statement/command but provided Dialect [" +
								dialect.getClass().getName() + "] did not return anything " +
								"from Dialect#getCurrentSchemaCommand"
				);
			}

			final Statement statement = connection.createStatement();
			try {
				final ResultSet resultSet = statement.executeQuery( dialect.getCurrentSchemaCommand() );
				try {
					if ( !resultSet.next() ) {
						return null;
					}
					return resultSet.getString( 1 );
				}
				finally {
					try {
						resultSet.close();
					}
					catch (SQLException ignore) {
					}
				}
			}
			finally {
				try {
					statement.close();
				}
				catch (SQLException ignore) {
				}
			}
		}
