	public static void execute(CommandLineArgs commandLineArgs) throws Exception {
		StandardServiceRegistry serviceRegistry = buildStandardServiceRegistry( commandLineArgs );
		try {
			final MetadataImplementor metadata = buildMetadata( commandLineArgs, serviceRegistry );

			new SchemaExport()
					.setHaltOnError( commandLineArgs.halt )
					.setOutputFile( commandLineArgs.outputFile )
					.setDelimiter( commandLineArgs.delimiter )
					.setFormat( commandLineArgs.format )
					.setManageNamespaces( commandLineArgs.manageNamespaces )
					.setImportFiles( commandLineArgs.importFile )
					.execute( commandLineArgs.targetTypes, commandLineArgs.action, metadata, serviceRegistry );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
