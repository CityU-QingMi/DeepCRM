	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		if ( value == null ) {
			return "null";
		}
		int length = Array.getLength(value);
		List list = new ArrayList(length);
		Type elemType = getElementType(factory);
		for ( int i=0; i<length; i++ ) {
			Object element = Array.get(value, i);
			if ( element == LazyPropertyInitializer.UNFETCHED_PROPERTY || !Hibernate.isInitialized( element ) ) {
				list.add( "<uninitialized>" );
			}
			else {
				list.add( elemType.toLoggableString( element, factory ) );
			}
		}
		return list.toString();
	}
