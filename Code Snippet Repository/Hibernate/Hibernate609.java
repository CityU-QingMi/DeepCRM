	public LoadedConfig loadConfigXmlFile(File cfgXmlFile) {
		try {
			final JaxbCfgHibernateConfiguration jaxbCfg = jaxbProcessorHolder.getValue().unmarshal(
					new FileInputStream( cfgXmlFile ),
					new Origin( SourceType.FILE, cfgXmlFile.getAbsolutePath() )
			);

			return LoadedConfig.consume( jaxbCfg );
		}
		catch (FileNotFoundException e) {
			throw new ConfigurationException(
					"Specified cfg.xml file [" + cfgXmlFile.getAbsolutePath() + "] does not exist"
			);
		}
	}
