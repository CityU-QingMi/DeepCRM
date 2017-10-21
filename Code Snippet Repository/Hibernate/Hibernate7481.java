	@Test
	public void testCollectionOfEmbeddablesWithConvertedAttributes() throws Exception {
		final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Disguise.class )
				.addAnnotatedClass( Traits.class )
				.buildMetadata();

		metadata.validate();

		final PersistentClass entityBinding = metadata.getEntityBinding( Disguise.class.getName() );

		// first check the singular composite...
		final Property singularTraitsProperty = entityBinding.getProperty( "singularTraits" );
		checkComposite( (Component) singularTraitsProperty.getValue() );

		// then check the plural composite...
		final Property pluralTraitsProperty = entityBinding.getProperty( "pluralTraits" );
		checkComposite( (Component) ( (org.hibernate.mapping.Set) pluralTraitsProperty.getValue() ).getElement() );

	}
