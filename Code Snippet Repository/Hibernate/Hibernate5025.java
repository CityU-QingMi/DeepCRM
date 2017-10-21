	protected String renderLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		if ( !Hibernate.isInitialized( value ) ) {
			return "<uninitialized>";
		}

		final List<String> list = new ArrayList<String>();
		Type elemType = getElementType( factory );
		Iterator itr = getElementsIterator( value );
		while ( itr.hasNext() ) {
			Object element = itr.next();
			if ( element == LazyPropertyInitializer.UNFETCHED_PROPERTY || !Hibernate.isInitialized( element ) ) {
				list.add( "<uninitialized>" );
			}
			else {
				list.add( elemType.toLoggableString( element, factory ) );
			}
		}
		return list.toString();
	}
