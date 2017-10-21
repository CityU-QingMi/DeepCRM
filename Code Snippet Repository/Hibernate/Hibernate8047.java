	@Override
	protected void prepareTest() throws Exception {
		doInHibernate( this::sessionFactory, s -> {
			s.save( keyValue );

			BaseTestEntity baseTestEntity1 = new BaseTestEntity();
			TestEntity testEntity = new TestEntity();
			Map<KeyValue, EmbeddableValue> map = new HashMap<>();
			map.put( keyValue, embeddableValue );
			testEntity.values = map;
			s.save( testEntity );
			baseTestEntity1.entities = new HashSet<TestEntity>();
			baseTestEntity1.entities.add( testEntity );
			s.save( baseTestEntity1 );

			KeyValue keyValue2 = new KeyValue( "key2" );
			s.save( keyValue2 );
			BaseTestEntity baseTestEntity2 = new BaseTestEntity();
			s.save( baseTestEntity2 );
			TestEntity testEntity2 = new TestEntity();
			Map<KeyValue, EmbeddableValue> map2 = new HashMap<>();
			map2.put( keyValue2, embeddableValue );
			testEntity2.values = map2;
			s.save( testEntity2 );
		} );
	}
