	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
		final TypedValue[] lhsTypedValues = lhs.getTypedValues( criteria, criteriaQuery );
		final TypedValue[] rhsTypedValues = rhs.getTypedValues( criteria, criteriaQuery );

		final TypedValue[] result = new TypedValue[ lhsTypedValues.length + rhsTypedValues.length ];
		System.arraycopy( lhsTypedValues, 0, result, 0, lhsTypedValues.length );
		System.arraycopy( rhsTypedValues, 0, result, lhsTypedValues.length, rhsTypedValues.length );
		return result;
	}
