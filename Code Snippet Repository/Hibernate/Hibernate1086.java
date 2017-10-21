	@Override
	protected CtMethod generateFieldWriter(
			CtClass managedCtClass,
			CtField persistentField,
			AttributeTypeDescriptor typeDescriptor) {

		String fieldName = persistentField.getName();
		String writerName = EnhancerConstants.PERSISTENT_FIELD_WRITER_PREFIX + fieldName;

		return MethodWriter.addSetter( managedCtClass, fieldName, writerName );
	}
