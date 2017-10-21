	@Override
	public void setPropertyValues(Object entity, Object[] values) throws HibernateException {
		boolean setAll = !entityMetamodel.hasLazyProperties();

		for ( int j = 0; j < entityMetamodel.getPropertySpan(); j++ ) {
			if ( setAll || values[j] != LazyPropertyInitializer.UNFETCHED_PROPERTY ) {
				setters[j].set( entity, values[j], getFactory() );
			}
		}
	}
