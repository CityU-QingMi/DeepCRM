	public AnnotationMetadataSourceProcessorImpl(
			ManagedResources managedResources,
			final MetadataBuildingContextRootImpl rootMetadataBuildingContext,
			IndexView jandexView) {
		this.rootMetadataBuildingContext = rootMetadataBuildingContext;
		this.jandexView = jandexView;

		this.reflectionManager = rootMetadataBuildingContext.getBuildingOptions().getReflectionManager();

		if ( CollectionHelper.isNotEmpty( managedResources.getAnnotatedPackageNames() ) ) {
			annotatedPackages.addAll( managedResources.getAnnotatedPackageNames() );
		}

		final AttributeConverterManager attributeConverterManager = new AttributeConverterManager( rootMetadataBuildingContext );

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Ewww.  This is temporary until we migrate to Jandex + StAX for annotation binding

		final JPAMetadataProvider jpaMetadataProvider = (JPAMetadataProvider) ( (MetadataProviderInjector) reflectionManager ).getMetadataProvider();
		for ( Binding xmlBinding : managedResources.getXmlMappingBindings() ) {
//			if ( !MappingBinder.DelayedOrmXmlData.class.isInstance( xmlBinding.getRoot() ) ) {
//				continue;
//			}
//
//			// convert the StAX representation in delayedOrmXmlData to DOM because that's what commons-annotations needs
//			final MappingBinder.DelayedOrmXmlData delayedOrmXmlData = (MappingBinder.DelayedOrmXmlData) xmlBinding.getRoot();
//			org.dom4j.Document dom4jDocument = toDom4jDocument( delayedOrmXmlData );
			if ( !org.dom4j.Document.class.isInstance( xmlBinding.getRoot() ) ) {
				continue;
			}
			org.dom4j.Document dom4jDocument = (Document) xmlBinding.getRoot();

			final List<String> classNames = jpaMetadataProvider.getXMLContext().addDocument( dom4jDocument );
			for ( String className : classNames ) {
				xClasses.add( toXClass( className, reflectionManager ) );
			}
		}
		jpaMetadataProvider.getXMLContext().applyDiscoveredAttributeConverters( attributeConverterManager );
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		final ClassLoaderService cls = rootMetadataBuildingContext.getBuildingOptions().getServiceRegistry().getService( ClassLoaderService.class );
		for ( String className : managedResources.getAnnotatedClassNames() ) {
			final Class annotatedClass = cls.classForName( className );
			categorizeAnnotatedClass( annotatedClass, attributeConverterManager );
		}

		for ( Class annotatedClass : managedResources.getAnnotatedClassReferences() ) {
			categorizeAnnotatedClass( annotatedClass, attributeConverterManager );
		}
	}
