	public void processSubclass(SubclassEntitySourceImpl subclassEntitySource) {
		final InheritanceType inheritanceType = Helper.interpretInheritanceType( subclassEntitySource.jaxbEntityMapping() );
		if ( hierarchyInheritanceType == InheritanceType.NO_INHERITANCE ) {
			hierarchyInheritanceType = inheritanceType;
		}
		else if ( hierarchyInheritanceType != inheritanceType ) {
			throw new MappingException( "Mixed inheritance strategies not supported", subclassEntitySource.getOrigin() );
		}

		collectedEntityNames.add( subclassEntitySource.getEntityNamingSource().getEntityName() );
	}
