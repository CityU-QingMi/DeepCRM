	private PersistentAttributeAccessMethods enhancePersistentAttribute(CtClass managedCtClass, CtField persistentField) {
		try {
			AttributeTypeDescriptor typeDescriptor = AttributeTypeDescriptor.resolve( managedCtClass, persistentField );
			return new PersistentAttributeAccessMethods(
					generateFieldReader( managedCtClass, persistentField, typeDescriptor ),
					generateFieldWriter( managedCtClass, persistentField, typeDescriptor )
			);
		}
		catch (Exception e) {
			final String msg = String.format(
					"Unable to enhance persistent attribute [%s:%s]",
					managedCtClass.getName(),
					persistentField.getName()
			);
			throw new EnhancementException( msg, e );
		}
	}
