	public ComponentType(TypeFactory.TypeScope typeScope, ComponentMetamodel metamodel) {
		this.typeScope = typeScope;
		// for now, just "re-flatten" the metamodel since this is temporary stuff anyway (HHH-1907)
		this.isKey = metamodel.isKey();
		this.propertySpan = metamodel.getPropertySpan();
		this.propertyNames = new String[propertySpan];
		this.propertyTypes = new Type[propertySpan];
		this.propertyValueGenerationStrategies = new ValueGeneration[propertySpan];
		this.propertyNullability = new boolean[propertySpan];
		this.cascade = new CascadeStyle[propertySpan];
		this.joinedFetch = new FetchMode[propertySpan];

		for ( int i = 0; i < propertySpan; i++ ) {
			StandardProperty prop = metamodel.getProperty( i );
			this.propertyNames[i] = prop.getName();
			this.propertyTypes[i] = prop.getType();
			this.propertyNullability[i] = prop.isNullable();
			this.cascade[i] = prop.getCascadeStyle();
			this.joinedFetch[i] = prop.getFetchMode();
			if ( !prop.isNullable() ) {
				hasNotNullProperty = true;
			}
			this.propertyValueGenerationStrategies[i] = prop.getValueGenerationStrategy();
		}

		this.entityMode = metamodel.getEntityMode();
		this.componentTuplizer = metamodel.getComponentTuplizer();
		this.createEmptyCompositesEnabled = metamodel.isCreateEmptyCompositesEnabled();
	}
