	public void validate(Mapping mapping) throws MappingException {
		Iterator iter = getPropertyIterator();
		while ( iter.hasNext() ) {
			Property prop = (Property) iter.next();
			if ( !prop.isValid( mapping ) ) {
				throw new MappingException(
						"property mapping has wrong number of columns: " +
								StringHelper.qualify( getEntityName(), prop.getName() ) +
								" type: " +
								prop.getType().getName()
				);
			}
		}
		checkPropertyDuplication();
		checkColumnDuplication();
	}
