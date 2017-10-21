	public static void insertNewArrayCode(MethodVisitor mv, int size, String arraytype) {
		insertOptimalLoad(mv, size);
		if (arraytype.length() == 1) {
			mv.visitIntInsn(NEWARRAY, CodeFlow.arrayCodeFor(arraytype));
		}
		else {
			if (arraytype.charAt(0) == '[') {
				// Handling the nested array case here. If vararg
				// is [[I then we want [I and not [I;
				if (CodeFlow.isReferenceTypeArray(arraytype)) {
					mv.visitTypeInsn(ANEWARRAY, arraytype+";");
				}
				else {
					mv.visitTypeInsn(ANEWARRAY, arraytype);
				}
			}
			else {
				mv.visitTypeInsn(ANEWARRAY, arraytype.substring(1));
			}
		}
	}
