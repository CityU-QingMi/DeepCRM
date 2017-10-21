		@Override
		public void setValue(PreparedStatement st, Enum value, int index) throws SQLException {
			final Object jdbcValue = value == null ? null : extractJdbcValue( value );

			final boolean traceEnabled = LOG.isTraceEnabled();
			if ( jdbcValue == null ) {
				if ( traceEnabled ) {
					LOG.trace(String.format("Binding null to parameter: [%s]", index));
				}
				st.setNull( index, getSqlType() );
				return;
			}

			if ( traceEnabled ) {
				LOG.trace(String.format("Binding [%s] to parameter: [%s]", jdbcValue, index));
			}
			st.setObject( index, jdbcValue, EnumType.this.sqlType );
		}
