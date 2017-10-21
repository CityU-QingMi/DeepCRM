	public static void main(String[] args) {
		try {
			final CommandLineArgs parsedArgs = CommandLineArgs.parseCommandLineArgs( args );
			final StandardServiceRegistry serviceRegistry = buildStandardServiceRegistry( parsedArgs );

			try {
				final MetadataImplementor metadata = buildMetadata( parsedArgs, serviceRegistry );

				new SchemaUpdate()
						.setOutputFile( parsedArgs.outputFile )
						.setDelimiter( parsedArgs.delimiter )
						.execute( parsedArgs.targetTypes, metadata, serviceRegistry );
			}
			finally {
				StandardServiceRegistryBuilder.destroy( serviceRegistry );
			}
		}
		catch (Exception e) {
			LOG.unableToRunSchemaUpdate( e );
			e.printStackTrace();
		}
	}
