	public static void insertArrayStore(MethodVisitor mv, String arrayElementType) {
		if (arrayElementType.length()==1) {
			switch (arrayElementType.charAt(0)) {
				case 'I': mv.visitInsn(IASTORE); break;
				case 'J': mv.visitInsn(LASTORE); break;
				case 'F': mv.visitInsn(FASTORE); break;
				case 'D': mv.visitInsn(DASTORE); break;
				case 'B': mv.visitInsn(BASTORE); break;
				case 'C': mv.visitInsn(CASTORE); break;
				case 'S': mv.visitInsn(SASTORE); break;
				case 'Z': mv.visitInsn(BASTORE); break;
				default:
					throw new IllegalArgumentException("Unexpected arraytype "+arrayElementType.charAt(0));
			}
		}
		else {
			mv.visitInsn(AASTORE);
		}
	}
