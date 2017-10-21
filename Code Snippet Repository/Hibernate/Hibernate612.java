	public Properties loadProperties(File file) {
		try {
			final InputStream stream = new FileInputStream( file );
			try {
				Properties properties = new Properties();
				properties.load( stream );
				return properties;
			}
			catch (IOException e) {
				throw new ConfigurationException(
						"Unable to apply settings from properties file [" + file.getAbsolutePath() + "]",
						e
				);
			}
			finally {
				try {
					stream.close();
				}
				catch (IOException e) {
					log.debug(
							String.format( "Unable to close properties file [%s] stream", file.getAbsolutePath() ),
							e
					);
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new ConfigurationException(
					"Unable locate specified properties file [" + file.getAbsolutePath() + "]",
					e
			);
		}
	}
