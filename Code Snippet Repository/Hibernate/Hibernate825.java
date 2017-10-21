	@Override
	public String[] getSynchronizedTableNames() {
		if ( CollectionHelper.isEmpty( jaxbEntityMapping.getSynchronize() ) ) {
			return StringHelper.EMPTY_STRINGS;
		}
		else {
			final int size = jaxbEntityMapping.getSynchronize().size();
			final String[] synchronizedTableNames = new String[size];
			for ( int i = 0; i < size; i++ ) {
				synchronizedTableNames[i] = jaxbEntityMapping.getSynchronize().get( i ).getTable();
			}
			return synchronizedTableNames;
		}
	}
