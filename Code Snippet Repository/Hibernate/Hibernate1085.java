	@Override
	protected CtMethod generateFieldReader(
			CtClass managedCtClass,
			CtField persistentField,
			AttributeTypeDescriptor typeDescriptor) {

		String fieldName = persistentField.getName();
		String readerName = EnhancerConstants.PERSISTENT_FIELD_READER_PREFIX + fieldName;

		return MethodWriter.addGetter( managedCtClass, fieldName, readerName );
	}
