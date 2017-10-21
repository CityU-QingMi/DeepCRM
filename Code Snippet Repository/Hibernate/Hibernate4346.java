	@Override
	@SuppressWarnings("")
	public CriteriaUpdate<T> set(String attributeName, Object value) {
		final Path attributePath = getRoot().get( attributeName );
		final Expression valueExpression = value == null
				? criteriaBuilder().nullLiteral( attributePath.getJavaType() )
				: criteriaBuilder().literal( value );
		addAssignment( attributePath, valueExpression );
		return this;
	}
