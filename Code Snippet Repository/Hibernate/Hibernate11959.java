	public String parseSqlIn(String fileName) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream( fileName );
		if ( is == null ) {
			throw new RuntimeException( "File " + fileName + " not found on Classpath." );
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader( is, Charset.forName( "UTF-8" ) )
			);

			StringWriter sw = new StringWriter();
			BufferedWriter writer = new BufferedWriter( sw );

			for ( int c = reader.read(); c != -1; c = reader.read() ) {
				writer.write( c );
			}
			writer.flush();
			return sw.toString();
		}
		finally {
			if ( reader != null ) {
				reader.close();
			}
			is.close();
		}
	}
