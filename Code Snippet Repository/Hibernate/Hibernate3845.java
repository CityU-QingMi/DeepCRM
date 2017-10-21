	public void appendRestrictions(String restrictions) {
		final String cleaned = cleanRestrictions( restrictions );
		if ( StringHelper.isEmpty( cleaned ) ) {
			return;
		}

		this.guesstimatedBufferSize += cleaned.length();

		if ( whereClause == null ) {
			whereClause = new StringBuilder( cleaned );
		}
		else {
			whereClause.append( " and " ).append( cleaned );
			this.guesstimatedBufferSize += 5;
		}
	}
