	private void addAssociationsToTheSetForOneProperty(String name, Type type, String prefix, SessionFactoryImplementor factory) {

		if ( type.isCollectionType() ) {
			CollectionType collType = (CollectionType) type;
			Type assocType = collType.getElementType( factory );
			addAssociationsToTheSetForOneProperty(name, assocType, prefix, factory);
		}
		//ToOne association
		else if ( type.isEntityType() || type.isAnyType() ) {
			associations.add( prefix + name );
		}
		else if ( type.isComponentType() ) {
			CompositeType componentType = (CompositeType) type;
			addAssociationsToTheSetForAllProperties(
					componentType.getPropertyNames(),
					componentType.getSubtypes(),
					(prefix.equals( "" ) ? name : prefix + name) + ".",
					factory);
		}
	}
