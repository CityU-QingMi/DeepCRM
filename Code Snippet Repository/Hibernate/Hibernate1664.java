	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
		return new TypedValue[] {
				criteriaQuery.getTypedValue(
						criteria,
						propertyName,
						value.toString().toLowerCase(Locale.ROOT)
				)
		};
	}
