	@After
	public void tearsDown() {
		if(!SQLServerDialect.class.isAssignableFrom( Dialect.getDialect().getClass() )) {
			return;
		}

		new SchemaExport().setHaltOnError( true )
				.setOutputFile( output.getAbsolutePath() )
				.setFormat( false )
				.drop( EnumSet.of( TargetType.DATABASE ), metadata );
		StandardServiceRegistryBuilder.destroy( ssr );
	}
