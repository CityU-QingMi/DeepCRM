	@Override
	public String[] findColumns(String propertyName, Criteria subcriteria)
			throws HibernateException {
		try {
			return getColumns( propertyName, subcriteria );
		}
		catch (HibernateException he) {
			//not found in inner query, try the outer query
			if ( outerQueryTranslator != null ) {
				return outerQueryTranslator.findColumns( propertyName, subcriteria );
			}
			else {
				throw he;
			}
		}
	}
