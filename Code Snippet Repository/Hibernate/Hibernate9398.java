	private boolean isCreateUniqueIndexGenerated(String tableName, String columnName) throws IOException {
		boolean matches = false;
		String regex = "create unique index uk_(.)* on " + tableName + " \\(" + columnName + "\\)";

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
