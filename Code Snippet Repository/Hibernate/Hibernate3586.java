	@Override
	public Type getTypeUsingProjection(Criteria subcriteria, String propertyName)
			throws HibernateException {

		//first look for a reference to a projection alias
		final Projection projection = rootCriteria.getProjection();
		Type[] projectionTypes = projection == null ?
				null :
				projection.getTypes( propertyName, subcriteria, this );

		if ( projectionTypes == null ) {
			try {
				//it does not refer to an alias of a projection,
				//look for a property
				return getType( subcriteria, propertyName );
			}
			catch (HibernateException he) {
				//not found in inner query , try the outer query
				if ( outerQueryTranslator != null ) {
					return outerQueryTranslator.getType( subcriteria, propertyName );
				}
				else {
					throw he;
				}
			}
		}
		else {
			if ( projectionTypes.length != 1 ) {
				//should never happen, i think
				throw new QueryException( "not a single-length projection: " + propertyName );
			}
			return projectionTypes[0];
		}
	}
