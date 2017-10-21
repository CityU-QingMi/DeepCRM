	private void addCompositeOwnerInterface(CtClass managedCtClass) throws NotFoundException, CannotCompileException {
		CtClass compositeOwnerCtClass = managedCtClass.getClassPool().get( CompositeOwner.class.getName() );

		// HHH-10540 only add the interface once
		for ( CtClass i : managedCtClass.getInterfaces() ) {
			if ( i.subclassOf( compositeOwnerCtClass ) ) {
				return;
			}
		}

		managedCtClass.addInterface( compositeOwnerCtClass );

		if ( enhancementContext.isCompositeClass( managedCtClass ) ) {
			// if a composite have a embedded field we need to implement the TRACKER_CHANGER_NAME method as well
			MethodWriter.write(
					managedCtClass,
					"public void %1$s(String name) {%n" +
							"  if (%2$s != null) { %2$s.callOwner(\".\" + name); }%n}",
					EnhancerConstants.TRACKER_CHANGER_NAME,
					EnhancerConstants.TRACKER_COMPOSITE_FIELD_NAME
			);
		}
	}
