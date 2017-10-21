	@Test
	public void testSimpleConvertUsage() throws MalformedURLException {
		// first some assertions of the metamodel
		PersistentClass entityBinding = metadata().getEntityBinding( TheEntity.class.getName() );
		assertNotNull( entityBinding );

		Property setAttributeBinding = entityBinding.getProperty( "set" );
		Collection setBinding = (Collection) setAttributeBinding.getValue();
		assertTyping( AttributeConverterTypeAdapter.class, setBinding.getElement().getType() );

		Property mapAttributeBinding = entityBinding.getProperty( "map" );
		IndexedCollection mapBinding = (IndexedCollection) mapAttributeBinding.getValue();
		assertTyping( AttributeConverterTypeAdapter.class, mapBinding.getIndex().getType() );
		assertTyping( AttributeConverterTypeAdapter.class, mapBinding.getElement().getType() );

		// now lets try to use the model, integration-testing-style!
		TheEntity entity = new TheEntity(1);

		Session s = openSession();
		s.beginTransaction();
		s.save( entity );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		TheEntity retrieved = (TheEntity) s.load( TheEntity.class, 1 );
		assertEquals( 1, retrieved.getSet().size() );
		assertEquals(new ValueType("set_value"), retrieved.getSet().iterator().next());
		assertEquals(1, retrieved.getMap().size());
		assertEquals(new ValueType("map_value"), retrieved.getMap().get(new ValueType("map_key")));
		s.delete( retrieved );
		s.getTransaction().commit();
		s.close();
	}
