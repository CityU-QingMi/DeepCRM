	public static String replace(
			String template,
			String placeholder,
			String replacement,
			boolean wholeWords,
			boolean encloseInParensIfNecessary) {
		if ( template == null ) {
			return null;
		}
		int loc = template.indexOf( placeholder );
		if ( loc < 0 ) {
			return template;
		}
		else {
			String beforePlaceholder = template.substring( 0, loc );
			String afterPlaceholder = template.substring( loc + placeholder.length() );
			return replace(
					beforePlaceholder,
					afterPlaceholder,
					placeholder,
					replacement,
					wholeWords,
					encloseInParensIfNecessary
			);
		}
	}
