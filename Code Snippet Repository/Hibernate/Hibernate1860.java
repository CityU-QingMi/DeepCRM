	private static String join(Iterator/*<String>*/ elements, StringTransformer elementTransformer, StringJoinTemplate template) {
		// todo : make this available via StringHelper?
		final StringBuilder buffer = new StringBuilder( template.getBeginning() );
		while ( elements.hasNext() ) {
			final String element = (String) elements.next();
			buffer.append( elementTransformer.transform( element ) );
			if ( elements.hasNext() ) {
				buffer.append( template.getSeparator() );
			}
		}
		return buffer.append( template.getEnding() ).toString();
	}
