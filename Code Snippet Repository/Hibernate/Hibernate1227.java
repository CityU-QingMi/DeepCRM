	private static boolean isIdClassPkOfTheAssociatedEntity(
			InheritanceState.ElementsToProcess elementsToProcess,
			XClass compositeClass,
			PropertyData inferredData,
			PropertyData baseInferredData,
			AccessType propertyAccessor,
			Map<XClass, InheritanceState> inheritanceStatePerClass,
			MetadataBuildingContext context) {
		if ( elementsToProcess.getIdPropertyCount() == 1 ) {
			final PropertyData idPropertyOnBaseClass = getUniqueIdPropertyFromBaseClass(
					inferredData,
					baseInferredData,
					propertyAccessor,
					context
			);
			final InheritanceState state = inheritanceStatePerClass.get( idPropertyOnBaseClass.getClassOrElement() );
			if ( state == null ) {
				return false; //while it is likely a user error, let's consider it is something that might happen
			}
			final XClass associatedClassWithIdClass = state.getClassWithIdClass( true );
			if ( associatedClassWithIdClass == null ) {
				//we cannot know for sure here unless we try and find the @EmbeddedId
				//Let's not do this thorough checking but do some extra validation
				final XProperty property = idPropertyOnBaseClass.getProperty();
				return property.isAnnotationPresent( ManyToOne.class )
						|| property.isAnnotationPresent( OneToOne.class );

			}
			else {
				final XClass idClass = context.getBuildingOptions().getReflectionManager().toXClass(
						associatedClassWithIdClass.getAnnotation( IdClass.class ).value()
				);
				return idClass.equals( compositeClass );
			}
		}
		else {
			return false;
		}
	}
