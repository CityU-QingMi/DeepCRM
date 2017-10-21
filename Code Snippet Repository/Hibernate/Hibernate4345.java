	@Override
	@SuppressWarnings("")
	public <Y, X extends Y> CriteriaUpdate<T> set(SingularAttribute<? super T, Y> singularAttribute, X value) {
		final Path<Y> attributePath = getRoot().get( singularAttribute );
		final Expression valueExpression = value == null
				? criteriaBuilder().nullLiteral( attributePath.getJavaType() )
				: criteriaBuilder().literal( value );
		addAssignment( attributePath, valueExpression );
		return this;
	}
