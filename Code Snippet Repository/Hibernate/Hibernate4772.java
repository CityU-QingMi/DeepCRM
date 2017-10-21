	@Override
	public void execute() throws BuildException {
		try {
			final StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder();
			configure( ssrBuilder );

			final StandardServiceRegistry ssr = ssrBuilder.build();

			try {
				final MetadataSources metadataSources = new MetadataSources( ssrBuilder.build() );
				configure( metadataSources );

				final MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
				configure( metadataBuilder, ssr );

				final MetadataImplementor metadata = (MetadataImplementor) metadataBuilder.build();

				new SchemaValidator().validate( metadata, ssr );
			}
			finally {
				StandardServiceRegistryBuilder.destroy( ssr );
			}
		}
		catch (HibernateException e) {
			throw new BuildException("Schema text failed: " + e.getMessage(), e);
		}
		catch (FileNotFoundException e) {
			throw new BuildException("File not found: " + e.getMessage(), e);
		}
		catch (IOException e) {
			throw new BuildException("IOException : " + e.getMessage(), e);
		}
		catch (BuildException e) {
			throw e;
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}
