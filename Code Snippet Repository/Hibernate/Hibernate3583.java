	@Override
	public String[] getColumnsUsingProjection(
			Criteria subcriteria,
			String propertyName) throws HibernateException {

		//first look for a reference to a projection alias
		final Projection projection = rootCriteria.getProjection();
		String[] projectionColumns = null;
		if ( projection != null ) {
			projectionColumns = ( projection instanceof EnhancedProjection ?
					( (EnhancedProjection) projection ).getColumnAliases( propertyName, 0, rootCriteria, this ) :
					projection.getColumnAliases( propertyName, 0 )
			);
		}
		if ( projectionColumns == null ) {
			//it does not refer to an alias of a projection,
			//look for a property
			try {
				return getColumns( propertyName, subcriteria );
			}
			catch (HibernateException he) {
				//not found in inner query , try the outer query
				if ( outerQueryTranslator != null ) {
					return outerQueryTranslator.getColumnsUsingProjection( subcriteria, propertyName );
				}
				else {
					throw he;
				}
			}
		}
		else {
			//it refers to an alias of a projection
			return projectionColumns;
		}
	}
