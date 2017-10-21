	private String adjustTemplateReferences(String template) {
		int templateLength = template.length();
		int startPos = template.indexOf( Template.TEMPLATE );
		while ( startPos != -1 && startPos < templateLength ) {
			int dotPos = startPos + TEMPLATE_MARKER_LENGTH;

			// from here we need to seek the end of the qualified identifier
			int pos = dotPos + 1;
			while ( pos < templateLength && isValidIdentifierCharacter( template.charAt( pos ) ) ) {
				pos++;
			}

			// At this point we know all 3 points in the template that are needed for replacement.
			// Basically we will be replacing the whole match with the bit following the dot, but will wrap
			// the replacement in curly braces.
			final String columnReference = template.substring( dotPos + 1, pos );
			final String replacement = "{" + columnReference + "}";
			template = template.replace( template.substring( startPos, pos ), replacement );
			columnReferences.add( columnReference );

			// prep for the next seek
			startPos = template.indexOf( Template.TEMPLATE, ( pos - TEMPLATE_MARKER_LENGTH ) + 1 );
			templateLength = template.length();
		}

		return template;
	}
