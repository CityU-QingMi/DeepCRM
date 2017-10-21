	private void handleCompositeField(CtClass managedCtClass, CtField persistentField, CtMethod fieldWriter)
			throws NotFoundException, CannotCompileException {
		if ( !enhancementContext.isCompositeClass( persistentField.getType() ) ||
				!PersistentAttributesHelper.hasAnnotation( persistentField, Embedded.class ) ) {
			return;
		}

		// make sure to add the CompositeOwner interface
		addCompositeOwnerInterface( managedCtClass );

		// cleanup previous owner
		fieldWriter.insertBefore(
				String.format(
						"if (%1$s != null) { ((%2$s) %1$s).%3$s(\"%1$s\"); }%n",
						persistentField.getName(),
						CompositeTracker.class.getName(),
						EnhancerConstants.TRACKER_COMPOSITE_CLEAR_OWNER
				)
		);

		// trigger track changes
		fieldWriter.insertAfter(
				String.format(
						"if (%1$s != null) { ((%2$s) %1$s).%4$s(\"%1$s\", (%3$s) this); }%n" +
								"%5$s(\"%1$s\");",
						persistentField.getName(),
						CompositeTracker.class.getName(),
						CompositeOwner.class.getName(),
						EnhancerConstants.TRACKER_COMPOSITE_SET_OWNER,
						EnhancerConstants.TRACKER_CHANGER_NAME
				)
		);
	}
