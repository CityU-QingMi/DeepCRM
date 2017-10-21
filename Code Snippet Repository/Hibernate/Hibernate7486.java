	@Test
	@TestForIssue( jiraKey = "" )
	public void testNestedTypeParameterAutoApplication() {
		final Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( SampleEntity.class )
				.getMetadataBuilder()
				.applyAttributeConverter( IntegerListConverter.class )
				.applyAttributeConverter( StringListConverter.class )
				.build();

		// lets make sure the auto-apply converters were applied properly...
		PersistentClass pc = metadata.getEntityBinding( SampleEntity.class.getName() );

		{
			Property prop = pc.getProperty( "someStrings" );
			AttributeConverterTypeAdapter type = assertTyping(
					AttributeConverterTypeAdapter.class,
					prop.getType()
			);
			assertTyping(
					StringListConverter.class,
					type.getAttributeConverter()
			);
		}

		{
			Property prop = pc.getProperty( "someIntegers" );
			AttributeConverterTypeAdapter type = assertTyping(
					AttributeConverterTypeAdapter.class,
					prop.getType()
			);
			assertTyping(
					IntegerListConverter.class,
					type.getAttributeConverter()
			);
		}
	}
