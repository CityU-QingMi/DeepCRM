	public static String replace(
			String beforePlaceholder,
			String afterPlaceholder,
			String placeholder,
			String replacement,
			boolean wholeWords,
			boolean encloseInParensIfNecessary) {
		final boolean actuallyReplace =
				!wholeWords
						|| afterPlaceholder.length() == 0
						|| !Character.isJavaIdentifierPart( afterPlaceholder.charAt( 0 ) );
		// We only need to check the left param to determine if the placeholder is already
		// enclosed in parentheses (HHH-10383)
		// Examples:
		// 1) "... IN (?1", we assume that "?1" does not need to be enclosed because there
		// there is already a right-parenthesis; we assume there will be a matching right-parenthesis.
		// 2) "... IN ?1", we assume that "?1" needs to be enclosed in parentheses, because there
		// is no left-parenthesis.

		// We need to check the placeholder is not used in `Order By FIELD(...)` (HHH-10502)
		// Examples:
		// " ... Order By FIELD(id,?1)",  after expand parameters, the sql is "... Order By FIELD(id,?,?,?)"
		boolean encloseInParens =
				actuallyReplace
						&& encloseInParensIfNecessary
						&& !( getLastNonWhitespaceCharacter( beforePlaceholder ) == '(' ) &&
						!( getLastNonWhitespaceCharacter( beforePlaceholder ) == ',' && getFirstNonWhitespaceCharacter(
								afterPlaceholder ) == ')' );
		StringBuilder buf = new StringBuilder( beforePlaceholder );
		if ( encloseInParens ) {
			buf.append( '(' );
		}
		buf.append( actuallyReplace ? replacement : placeholder );
		if ( encloseInParens ) {
			buf.append( ')' );
		}
		buf.append(
				replace(
						afterPlaceholder,
						placeholder,
						replacement,
						wholeWords,
						encloseInParensIfNecessary
				)
		);
		return buf.toString();
	}
