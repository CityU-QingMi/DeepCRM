	private static String[] extractSynchronizedTableNames(PluralAttributeInfo pluralAttributeElement) {
		if ( pluralAttributeElement.getSynchronize().isEmpty() ) {
			return new String[0];
		}

		final String[] names = new String[ pluralAttributeElement.getSynchronize().size() ];
		int i = 0;
		for ( JaxbHbmSynchronizeType jaxbHbmSynchronizeType : pluralAttributeElement.getSynchronize() ) {
			names[i++] = jaxbHbmSynchronizeType.getTable();
		}

		return names;
	}
