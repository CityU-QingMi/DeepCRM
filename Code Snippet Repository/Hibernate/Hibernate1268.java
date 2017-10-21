	Ejb3JoinColumn[] buildDefaultJoinColumnsForXToOne(XProperty property, PropertyData inferredData) {
		Ejb3JoinColumn[] joinColumns;
		JoinTable joinTableAnn = propertyHolder.getJoinTable( property );
		if ( joinTableAnn != null ) {
			joinColumns = Ejb3JoinColumn.buildJoinColumns(
					joinTableAnn.inverseJoinColumns(),
					null,
					entityBinder.getSecondaryTables(),
					propertyHolder,
					inferredData.getPropertyName(),
					buildingContext
			);
			if ( StringHelper.isEmpty( joinTableAnn.name() ) ) {
				throw new AnnotationException(
						"JoinTable.name() on a @ToOne association has to be explicit: "
								+ BinderHelper.getPath( propertyHolder, inferredData )
				);
			}
		}
		else {
			OneToOne oneToOneAnn = property.getAnnotation( OneToOne.class );
			String mappedBy = oneToOneAnn != null
					? oneToOneAnn.mappedBy()
					: null;
			joinColumns = Ejb3JoinColumn.buildJoinColumns(
					null,
					mappedBy,
					entityBinder.getSecondaryTables(),
					propertyHolder,
					inferredData.getPropertyName(),
					buildingContext
			);
		}
		return joinColumns;
	}
