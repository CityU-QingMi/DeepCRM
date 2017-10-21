	public Identifier(String text, boolean quoted) {
		if ( StringHelper.isEmpty( text ) ) {
			throw new IllegalIdentifierException( "Identifier text cannot be null" );
		}
		if ( isQuoted( text ) ) {
			throw new IllegalIdentifierException( "Identifier text should not contain quote markers (` or \")" );
		}
		this.text = text;
		this.isQuoted = quoted;
	}
