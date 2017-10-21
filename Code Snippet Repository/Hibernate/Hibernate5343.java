	@Test
	@TestForIssue(jiraKey = "")
	public void testAssociatedClassException() throws SQLException {
		final Collection collection = mock(Collection.class);
		final Map persistentClasses = mock(Map.class);
		final XClass collectionType = mock(XClass.class);
		final MetadataBuildingContext buildingContext = mock(MetadataBuildingContext.class);
		final InFlightMetadataCollector inFly = mock(InFlightMetadataCollector.class);
		final PersistentClass persistentClass = mock(PersistentClass.class);
		final Table table = mock(Table.class);
		
		when(buildingContext.getMetadataCollector()).thenReturn(inFly);
		when(persistentClasses.get(null)).thenReturn(null);
		when(collection.getOwner()).thenReturn(persistentClass);
		when(collectionType.getName()).thenReturn("List");
		when(persistentClass.getTable()).thenReturn(table);
		when(table.getName()).thenReturn("Hibernate");
		
		CollectionBinder collectionBinder = new CollectionBinder(false) {
			@Override
			protected Collection createCollection(PersistentClass persistentClass) {
				return null;
			}

			{
				final PropertyHolder propertyHolder = Mockito.mock(PropertyHolder.class);
				when(propertyHolder.getClassName()).thenReturn( CollectionBinderTest.class.getSimpleName() );
				this.propertyName = "abc";
				this.propertyHolder = propertyHolder;
			}
		};

		String expectMessage = "Association [abc] for entity [CollectionBinderTest] references unmapped class [List]";
		try {
			collectionBinder.bindOneToManySecondPass(collection, persistentClasses, null, collectionType, false, false, buildingContext, null);
		} catch (MappingException e) {
			assertEquals(expectMessage, e.getMessage());
		}
	}
