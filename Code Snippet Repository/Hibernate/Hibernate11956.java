	private Properties readProperties(String propertyFile) {
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream( propertyFile );
			if ( is == null ) {
				throw new RuntimeException( String.format( "File %s not found on classpath.", propertyFile ) );
			}
			Properties properties = new Properties();
			properties.load( is );
			return properties;
		}
		catch (IOException e) {
			throw (new RuntimeException( e ));
		}
		finally {
			if ( is != null ) {
				try {
					is.close();
				}
				catch (IOException e) {
					//nothing to do
				}
			}
		}
	}
