	protected void checkPropertyColumnDuplication(Set distinctColumns, Iterator properties)
			throws MappingException {
		while ( properties.hasNext() ) {
			Property prop = (Property) properties.next();
			if ( prop.getValue() instanceof Component ) { //TODO: remove use of instanceof!
				Component component = (Component) prop.getValue();
				checkPropertyColumnDuplication( distinctColumns, component.getPropertyIterator() );
			}
			else {
				if ( prop.isUpdateable() || prop.isInsertable() ) {
					checkColumnDuplication( distinctColumns, prop.getColumnIterator() );
				}
			}
		}
	}
