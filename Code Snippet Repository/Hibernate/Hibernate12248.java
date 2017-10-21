	public static String getMetaModelSourceAsString(Class<?> clazz) {
		File sourceFile = getMetaModelSourceFileFor( clazz );
		StringBuilder contents = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader( new FileReader( sourceFile ) );
			try {
				String line;
/**/
/**/
/**/
/**/
/**/
/**/
				while ( ( line = input.readLine() ) != null ) {
					contents.append( line );
					contents.append( System.lineSeparator() );
				}
			}
			finally {
				input.close();
			}
		}
		catch ( IOException ex ) {
			ex.printStackTrace();
		}

		return contents.toString();
	}
