	static Implementation wrap(
			TypeDescription managedCtClass,
			ByteBuddyEnhancementContext enhancementContext,
			FieldDescription persistentField,
			Implementation implementation) {
		if ( enhancementContext.doDirtyCheckingInline( managedCtClass ) ) {

			if ( enhancementContext.isCompositeClass( managedCtClass ) ) {
				implementation = Advice.to( CodeTemplates.CompositeDirtyCheckingHandler.class ).wrap( implementation );
			}
			else if ( !EnhancerImpl.isAnnotationPresent( persistentField, Id.class )
					&& !EnhancerImpl.isAnnotationPresent( persistentField, EmbeddedId.class )
					&& !( persistentField.getType().asErasure().isAssignableTo( Collection.class )
					&& enhancementContext.isMappedCollection( persistentField ) ) ) {
				implementation = new InlineDirtyCheckingHandler( implementation, managedCtClass, persistentField.asDefined() );
			}

			if ( enhancementContext.isCompositeClass( persistentField.getType().asErasure() )
					&& EnhancerImpl.isAnnotationPresent( persistentField, Embedded.class ) ) {

				implementation = Advice.withCustomMapping()
						.bind( CodeTemplates.FieldValue.class, persistentField )
						.bind( CodeTemplates.FieldName.class, persistentField.getName() )
						.to( CodeTemplates.CompositeFieldDirtyCheckingHandler.class )
						.wrap( implementation );
			}
		}
		return implementation;
	}
