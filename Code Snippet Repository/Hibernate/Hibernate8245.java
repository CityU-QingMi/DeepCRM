		@Override
		public void nullSafeSet(
				PreparedStatement preparedStatement,
				Object value,
				int index,
				SharedSessionContractImplementor sessionImplementor) throws HibernateException, SQLException {
			CustomId customId = (CustomId) value;

			if ( customId == null ) {
				preparedStatement.setNull( index, SQL_TYPE.sqlType() );
			}
			else {
				preparedStatement.setLong( index, customId.getValue() );
			}
		}
