	@Override
	public String[] getColumns(
			String propertyName,
			Criteria subcriteria) throws HibernateException {
		return getPropertyMapping( getEntityName( subcriteria, propertyName ) )
				.toColumns(
						getSQLAlias( subcriteria, propertyName ),
						getPropertyName( propertyName )
				);
	}
