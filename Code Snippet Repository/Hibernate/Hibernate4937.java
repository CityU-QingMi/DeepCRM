	public ComponentMetamodel(Component component, MetadataBuildingOptions metadataBuildingOptions) {
//		this.sessionFactory = sessionFactory;
		this.role = component.getRoleName();
		this.isKey = component.isKey();
		propertySpan = component.getPropertySpan();
		properties = new StandardProperty[propertySpan];
		Iterator itr = component.getPropertyIterator();
		int i = 0;
		while ( itr.hasNext() ) {
			Property property = ( Property ) itr.next();
			properties[i] = PropertyFactory.buildStandardProperty( property, false );
			propertyIndexes.put( property.getName(), i );
			i++;
		}

		entityMode = component.hasPojoRepresentation() ? EntityMode.POJO : EntityMode.MAP;

		// todo : move this to SF per HHH-3517; also see HHH-1907 and ComponentMetamodel
		final ComponentTuplizerFactory componentTuplizerFactory = new ComponentTuplizerFactory( metadataBuildingOptions );
		final String tuplizerClassName = component.getTuplizerImplClassName( entityMode );
		this.componentTuplizer = tuplizerClassName == null ? componentTuplizerFactory.constructDefaultTuplizer(
				entityMode,
				component
		) : componentTuplizerFactory.constructTuplizer( tuplizerClassName, component );

		final ConfigurationService cs = component.getMetadata().getMetadataBuildingOptions().getServiceRegistry()
				.getService(ConfigurationService.class);

		this.createEmptyCompositesEnabled = ConfigurationHelper.getBoolean(
				Environment.CREATE_EMPTY_COMPOSITES_ENABLED,
				cs.getSettings(),
				false
		);
	}
