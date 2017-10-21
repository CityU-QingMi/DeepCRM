	private boolean isUniqueConstraintGenerated(String tableName, String columnName) throws IOException {
		boolean matches = false;
		final String regex = getDialect().getAlterTableString( tableName ) + " add constraint uk_(.)* unique \\(" + columnName + "\\)";

		final String fileContent = new String( Files.readAllBytes( output.toPath() ) ).toLowerCase();
		final String[] split = fileContent.split( System.lineSeparator() );
		Pattern p = Pattern.compile( regex );
		for ( String line : split ) {
			final Matcher matcher = p.matcher( line );
			if ( matcher.matches() ) {
				matches = true;
			}
		}
		return matches;
	}
