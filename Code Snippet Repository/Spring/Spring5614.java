	public static void insertOptimalLoad(MethodVisitor mv, int value) {
		if (value < 6) {
			mv.visitInsn(ICONST_0+value);
		}
		else if (value < Byte.MAX_VALUE) {
			mv.visitIntInsn(BIPUSH, value);
		}
		else if (value < Short.MAX_VALUE) {
			mv.visitIntInsn(SIPUSH, value);
		}
		else {
			mv.visitLdcInsn(value);
		}
	}
