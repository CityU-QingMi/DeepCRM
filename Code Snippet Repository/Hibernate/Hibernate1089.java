	public void enhance(CtClass managedCtClass) {
		final IdentityHashMap<String, PersistentAttributeAccessMethods> attrDescriptorMap = new IdentityHashMap<String, PersistentAttributeAccessMethods>();

		for ( CtField persistentField : collectPersistentFields( managedCtClass ) ) {
			attrDescriptorMap.put(
					persistentField.getName(), enhancePersistentAttribute(
							managedCtClass,
							persistentField
					)
			);
		}

		// find all references to the transformed fields and replace with calls to the added reader/writer methods
		enhanceAttributesAccess( managedCtClass, attrDescriptorMap );

		// same thing for direct access to fields of other entities
		if ( this.enhancementContext.doExtendedEnhancement( managedCtClass ) ) {
			extendedEnhancement( managedCtClass );
		}
	}
