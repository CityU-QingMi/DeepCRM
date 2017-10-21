	@Test
	@SuppressWarnings("")
	public void testGeneratedSql() {

		Map settings = new HashMap();
		settings.putAll( Environment.getProperties() );
		settings.put( AvailableSettings.DIALECT, SQLServerDialect.class.getName() );

		ServiceRegistry serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( settings );

		try {
			MetadataSources ms = new MetadataSources( serviceRegistry );
			ms.addAnnotatedClass(Address.class);
			ms.addAnnotatedClass(Person.class);

			final Metadata metadata = ms.buildMetadata();
			final List<String> commands = new SchemaCreatorImpl( serviceRegistry ).generateCreationCommands( metadata, false );
			for (String s : commands) {
                log.debug( s );
			}
			String expectedMappingTableSql = "create table personAddress (address_id numeric(19,0), " +
					"person_id numeric(19,0) not null, primary key (person_id))";

            Assert.assertEquals( "Wrong SQL", expectedMappingTableSql, commands.get( 2 ) );
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
		finally {
			ServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
