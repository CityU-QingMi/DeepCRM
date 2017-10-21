	public LoadedConfig loadConfigXmlResource(String cfgXmlResourceName) {
		final InputStream stream = bootstrapServiceRegistry.getService( ClassLoaderService.class ).locateResourceStream( cfgXmlResourceName );
		if ( stream == null ) {
			throw new ConfigurationException( "Could not locate cfg.xml resource [" + cfgXmlResourceName + "]" );
		}

		try {
			final JaxbCfgHibernateConfiguration jaxbCfg = jaxbProcessorHolder.getValue().unmarshal(
					stream,
					new Origin( SourceType.RESOURCE, cfgXmlResourceName )
			);

			return LoadedConfig.consume( jaxbCfg );
		}
		finally {
			try {
				stream.close();
			}
			catch (IOException e) {
				log.debug( "Unable to close cfg.xml resource stream", e );
			}
		}
	}
