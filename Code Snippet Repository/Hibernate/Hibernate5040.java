	@Override
	public Object[] getPropertyValues(Object component, EntityMode entityMode)
			throws HibernateException {
		if (component == null) {
			component = new Object[propertySpan];
		}
		if ( component instanceof Object[] ) {
			// A few calls to hashCode pass the property values already in an 
			// Object[] (ex: QueryKey hash codes for cached queries).
			// It's easiest to just check for the condition here prior to
			// trying reflection.
			return (Object[]) component;
		}
		else {
			return componentTuplizer.getPropertyValues( component );
		}
	}
