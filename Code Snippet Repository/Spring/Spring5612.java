	public static void insertCheckCast(MethodVisitor mv, @Nullable String descriptor) {
		if (descriptor != null && descriptor.length() != 1) {
			if (descriptor.charAt(0) == '[') {
				if (isPrimitiveArray(descriptor)) {
					mv.visitTypeInsn(CHECKCAST, descriptor);
				}
				else {
					mv.visitTypeInsn(CHECKCAST, descriptor + ";");
				}
			}
			else {
				if (!descriptor.equals("Ljava/lang/Object")) {
					// This is chopping off the 'L' to leave us with "java/lang/String"
					mv.visitTypeInsn(CHECKCAST, descriptor.substring(1));
				}
			}
		}
	}
