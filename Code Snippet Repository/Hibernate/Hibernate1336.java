	public ElementsToProcess getElementsToProcess() {
		if ( elementsToProcess == null ) {
			InheritanceState inheritanceState = inheritanceStatePerClass.get( clazz );
			assert !inheritanceState.isEmbeddableSuperclass();


			getMappedSuperclassesTillNextEntityOrdered();

			accessType = determineDefaultAccessType();

			ArrayList<PropertyData> elements = new ArrayList<PropertyData>();
			int idPropertyCount = 0;

			for ( XClass classToProcessForMappedSuperclass : classesToProcessForMappedSuperclass ) {
				PropertyContainer propertyContainer = new PropertyContainer(
						classToProcessForMappedSuperclass,
						clazz,
						accessType
				);
				int currentIdPropertyCount = AnnotationBinder.addElementsOfClass(
						elements,
						propertyContainer,
						buildingContext
				);
				idPropertyCount += currentIdPropertyCount;
			}

			if ( idPropertyCount == 0 && !inheritanceState.hasParents() ) {
				throw new AnnotationException( "No identifier specified for entity: " + clazz.getName() );
			}
			elements.trimToSize();
			elementsToProcess = new ElementsToProcess( elements, idPropertyCount );
		}
		return elementsToProcess;
	}
