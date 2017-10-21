		@Override
		public Object nullSafeGet(
				ResultSet resultSet,
				String[] names,
				SharedSessionContractImplementor sessionImplementor,
				Object o) throws HibernateException, SQLException {
			Long value = resultSet.getLong( names[0] );

			return new CustomId( value );
		}
