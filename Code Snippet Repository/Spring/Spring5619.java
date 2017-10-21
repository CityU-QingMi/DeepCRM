	public static void insertUnboxNumberInsns(
			MethodVisitor mv, char targetDescriptor, @Nullable String stackDescriptor) {

		if (stackDescriptor == null) {
			return;
		}

		switch (targetDescriptor) {
			case 'D':
				if (stackDescriptor.equals("Ljava/lang/Object")) {
					mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
				}
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "doubleValue", "()D", false);
				break;
			case 'F':
				if (stackDescriptor.equals("Ljava/lang/Object")) {
					mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
				}
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "floatValue", "()F", false);
				break;
			case 'J':
				if (stackDescriptor.equals("Ljava/lang/Object")) {
					mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
				}
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "longValue", "()J", false);
				break;
			case 'I':
				if (stackDescriptor.equals("Ljava/lang/Object")) {
					mv.visitTypeInsn(CHECKCAST, "java/lang/Number");
				}
				mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Number", "intValue", "()I", false);
				break;
			// does not handle Z, B, C, S
			default:
				throw new IllegalArgumentException("Unboxing should not be attempted for descriptor '" + targetDescriptor + "'");
		}
	}
