	private void checkPropertyDuplication() throws MappingException {
		HashSet<String> names = new HashSet<String>();
		Iterator iter = getPropertyIterator();
		while ( iter.hasNext() ) {
			Property prop = (Property) iter.next();
			if ( !names.add( prop.getName() ) ) {
				throw new MappingException( "Duplicate property mapping of " + prop.getName() + " found in " + getEntityName() );
			}
		}
	}
