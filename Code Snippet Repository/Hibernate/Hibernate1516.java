	private static String getJavaAttributeNameFromXMLOne(String attributeName) {
		StringBuilder annotationAttributeName = new StringBuilder( attributeName );
		int index = annotationAttributeName.indexOf( WORD_SEPARATOR );
		while ( index != -1 ) {
			annotationAttributeName.deleteCharAt( index );
			annotationAttributeName.setCharAt(
					index, Character.toUpperCase( annotationAttributeName.charAt( index ) )
			);
			index = annotationAttributeName.indexOf( WORD_SEPARATOR );
		}
		return annotationAttributeName.toString();
	}
