	private void addMappedSuperClassInMetadata(PersistentClass persistentClass) {
		//add @MappedSuperclass in the metadata
		// classes from 0 to n-1 are @MappedSuperclass and should be linked
		org.hibernate.mapping.MappedSuperclass mappedSuperclass = null;
		final InheritanceState superEntityState =
				InheritanceState.getInheritanceStateOfSuperEntity( clazz, inheritanceStatePerClass );
		PersistentClass superEntity =
				superEntityState != null ?
						buildingContext.getMetadataCollector().getEntityBinding( superEntityState.getClazz().getName() ) :
						null;
		final int lastMappedSuperclass = classesToProcessForMappedSuperclass.size() - 1;
		for ( int index = 0; index < lastMappedSuperclass; index++ ) {
			org.hibernate.mapping.MappedSuperclass parentSuperclass = mappedSuperclass;
			final Class<?> type = buildingContext.getBuildingOptions().getReflectionManager()
					.toClass( classesToProcessForMappedSuperclass.get( index ) );
			//add MAppedSuperclass if not already there
			mappedSuperclass = buildingContext.getMetadataCollector().getMappedSuperclass( type );
			if ( mappedSuperclass == null ) {
				mappedSuperclass = new org.hibernate.mapping.MappedSuperclass( parentSuperclass, superEntity );
				mappedSuperclass.setMappedClass( type );
				buildingContext.getMetadataCollector().addMappedSuperclass( type, mappedSuperclass );
			}
		}
		if ( mappedSuperclass != null ) {
			persistentClass.setSuperMappedSuperclass( mappedSuperclass );
		}
	}
