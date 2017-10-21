	@Override
	public Type[] getTypes(String alias, Criteria criteria, CriteriaQuery criteriaQuery) {
		for ( Projection projection : elements ) {
			final Type[] types = projection.getTypes( alias, criteria, criteriaQuery );
			if ( types != null ) {
				return types;
			}
		}
		return null;
	}
