	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		//the following two lines were added to ensure that this.params is not null, which
		//can happen with two-deep nested subqueries
		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		createAndSetInnerQuery( criteriaQuery, factory );

		final Type[] ppTypes = params.getPositionalParameterTypes();
		final Object[] ppValues = params.getPositionalParameterValues();
		final TypedValue[] tv = new TypedValue[ppTypes.length];
		for ( int i=0; i<ppTypes.length; i++ ) {
			tv[i] = new TypedValue( ppTypes[i], ppValues[i] );
		}
		return tv;
	}
