	public static void insertNumericUnboxOrPrimitiveTypeCoercion(
			MethodVisitor mv, @Nullable String stackDescriptor, char targetDescriptor) {

		if (!CodeFlow.isPrimitive(stackDescriptor)) {
			CodeFlow.insertUnboxNumberInsns(mv, targetDescriptor, stackDescriptor);
		}
		else {
			CodeFlow.insertAnyNecessaryTypeConversionBytecodes(mv, targetDescriptor, stackDescriptor);
		}
	}
