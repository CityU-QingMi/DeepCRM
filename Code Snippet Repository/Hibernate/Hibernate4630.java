		public List processResultSet(ResultSet resultSet) throws SQLException {
			if ( needsDiscovery ) {
				super.autoDiscoverTypes( resultSet );
				// todo : EntityAliases discovery
				needsDiscovery = false;
			}
			return super.processResultSet(
					resultSet,
					queryParameters,
					session,
					true,
					null,
					Integer.MAX_VALUE,
					Collections.emptyList()
			);
		}
