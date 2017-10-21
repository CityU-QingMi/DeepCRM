	public void testMigrationOfForeignKeys() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {
			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Box.class )
					.addAnnotatedClass( Thing.class )
					.buildMetadata();
			metadata.validate();

			// first create the schema...
			new SchemaExport().create( EnumSet.of( TargetType.DATABASE ), metadata );

			try {
				// try to update the just created schema
				new SchemaUpdate().execute( EnumSet.of( TargetType.DATABASE ), metadata );
			}
			finally {
				// clean up
				new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), metadata );
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
