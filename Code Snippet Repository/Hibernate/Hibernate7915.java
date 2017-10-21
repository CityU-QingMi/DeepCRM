	@SuppressWarnings( {""})
	@Test
	public void testAnyMappingReference() {
		Session s = openSession();
		s.beginTransaction();

		PropertyValue redValue = new StringPropertyValue( "red" );
		PropertyValue loneliestNumberValue = new IntegerPropertyValue( 1 );

		Long id;
		PropertySet ps = new PropertySet( "my properties" );
		ps.setSomeSpecificProperty( redValue );
		ps.getGeneralProperties().put( "the loneliest number", loneliestNumberValue );
		ps.getGeneralProperties().put( "i like", new StringPropertyValue( "pina coladas" ) );
		ps.getGeneralProperties().put( "i also like", new StringPropertyValue( "getting caught in the rain" ) );
		s.save( ps );

		s.getTransaction().commit();
		id = ps.getId();
		s.clear();
		s.beginTransaction();

		// TODO : setEntity() currently will not work here, but that would be *very* nice
		// does not work because the corresponding EntityType is then used as the "bind type" rather
		// than the "discovered" AnyType...
		s.createQuery( "from PropertySet p where p.someSpecificProperty = :ssp" ).setParameter( "ssp", redValue ).list();

		s.createQuery( "from PropertySet p where p.someSpecificProperty.id is not null" ).list();

		s.createQuery( "from PropertySet p join p.generalProperties gp where gp.id is not null" ).list();

		s.delete( s.load( PropertySet.class, id ) );

		s.getTransaction().commit();
		s.close();
	}
