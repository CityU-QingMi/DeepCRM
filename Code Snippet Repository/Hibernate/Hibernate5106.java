		@Override
		public Enum getValue(ResultSet rs, String[] names) throws SQLException {
			final String value = rs.getString( names[0] );

			final boolean traceEnabled = LOG.isTraceEnabled();
			if ( rs.wasNull() ) {
				if ( traceEnabled ) {
					LOG.trace(String.format("Returning null as column [%s]", names[0]));
				}
				return null;
			}

			final Enum enumValue = fromName( value );
			if ( traceEnabled ) {
				LOG.trace(String.format("Returning [%s] as column [%s]", enumValue, names[0]));
			}
			return enumValue;
		}
