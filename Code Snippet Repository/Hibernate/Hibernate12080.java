	private String getIgnoreMessage(String reason, String comment, String jiraKey) {
		StringBuilder buffer = new StringBuilder( reason );
		if ( StringHelper.isNotEmpty( comment ) ) {
			buffer.append( "; " ).append( comment );
		}

		if ( StringHelper.isNotEmpty( jiraKey ) ) {
			buffer.append( " (" ).append( jiraKey ).append( ')' );
		}

		return buffer.toString();
	}
