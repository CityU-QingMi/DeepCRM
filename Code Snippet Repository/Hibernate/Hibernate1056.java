	private Implementation fieldWriter(FieldDescription enhancedField) {
		Implementation implementation;
		if ( !enhancementContext.hasLazyLoadableAttributes( managedCtClass ) || !enhancementContext.isLazyLoadable( enhancedField ) ) {
			if ( enhancedField.getDeclaringType().asErasure().equals( managedCtClass ) ) {
				implementation = FieldAccessor.ofField( enhancedField.getName() ).in( enhancedField.getDeclaringType().asErasure() );
			}
			else {
				implementation = new Implementation.Simple( new FieldMethodWriter( managedCtClass, enhancedField ) );
			}
		}
		else {
			implementation = new Implementation.Simple( FieldWriterAppender.of( managedCtClass, enhancedField ) );
		}
		implementation = InlineDirtyCheckingHandler.wrap( managedCtClass, enhancementContext, enhancedField, implementation );
		return BiDirectionalAssociationHandler.wrap( managedCtClass, enhancementContext, enhancedField, implementation );
	}
