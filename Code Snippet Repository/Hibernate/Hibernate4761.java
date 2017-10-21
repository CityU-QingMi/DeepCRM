	@Override
	public void execute() throws BuildException {
		log( "Running Hibernate Core SchemaUpdate." );
		log( "This is an Ant task supporting only mapping files, if you want to use annotations see http://tools.hibernate.org." );

		try {
			final StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder();
			configure( ssrBuilder );

			final StandardServiceRegistry ssr = ssrBuilder.build();

			final MetadataSources metadataSources = new MetadataSources( ssr );
			configure( metadataSources );

			final MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
			configure( metadataBuilder, ssr );

			final MetadataImplementor metadata = (MetadataImplementor) metadataBuilder.build();

			new SchemaUpdate()
					.setOutputFile( outputFile.getPath() )
					.setDelimiter( delimiter )
					.setHaltOnError( haltOnError )
					.execute( TargetTypeHelper.parseLegacyCommandLineOptions( !quiet, !text, outputFile.getPath() ), metadata );
		}
		catch (HibernateException e) {
			throw new BuildException( "Schema text failed: " + e.getMessage(), e );
		}
		catch (FileNotFoundException e) {
			throw new BuildException( "File not found: " + e.getMessage(), e );
		}
		catch (IOException e) {
			throw new BuildException( "IOException : " + e.getMessage(), e );
		}
		catch (BuildException e) {
			throw e;
		}
		catch (Exception e) {
			throw new BuildException( e );
		}
	}
