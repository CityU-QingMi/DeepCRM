	public static String replaceOnce(String template, String placeholder, String replacement) {
		if ( template == null ) {
			return null;  // returnign null!
		}
		int loc = template.indexOf( placeholder );
		if ( loc < 0 ) {
			return template;
		}
		else {
			return template.substring( 0, loc ) + replacement + template.substring( loc + placeholder.length() );
		}
	}
