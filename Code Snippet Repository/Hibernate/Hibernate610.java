	public LoadedConfig loadConfigXmlUrl(URL url) {
		try {
			final InputStream stream = url.openStream();
			try {
				final JaxbCfgHibernateConfiguration  jaxbCfg = jaxbProcessorHolder.getValue().unmarshal(
						stream,
						new Origin( SourceType.URL, url.toExternalForm() )
				);

				return LoadedConfig.consume( jaxbCfg );
			}
			finally {
				try {
					stream.close();
				}
				catch (IOException e) {
					log.debug( "Unable to close cfg.xml URL stream", e );
				}
			}
		}
		catch (IOException e) {
			throw new ConfigurationException( "Could not access given cfg.xml URL input stream", e );
		}
	}
