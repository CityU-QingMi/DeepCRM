	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		if (this.exitTypeDescriptor == "Ljava/lang/String") {
			mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
			walk(mv, cf, getLeftOperand());
			walk(mv, cf, getRightOperand());
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		}
		else {
			this.children[0].generateCode(mv, cf);
			String leftDesc = this.children[0].exitTypeDescriptor;
			String exitDesc = this.exitTypeDescriptor;
			Assert.state(exitDesc != null, "No exit type descriptor");
			char targetDesc = exitDesc.charAt(0);
			CodeFlow.insertNumericUnboxOrPrimitiveTypeCoercion(mv, leftDesc, targetDesc);
			if (this.children.length > 1) {
				cf.enterCompilationScope();
				this.children[1].generateCode(mv, cf);
				String rightDesc = this.children[1].exitTypeDescriptor;
				cf.exitCompilationScope();
				CodeFlow.insertNumericUnboxOrPrimitiveTypeCoercion(mv, rightDesc, targetDesc);
				switch (targetDesc) {
					case 'I':
						mv.visitInsn(IADD);
						break;
					case 'J':
						mv.visitInsn(LADD);
						break;
					case 'F': 
						mv.visitInsn(FADD);
						break;
					case 'D':
						mv.visitInsn(DADD);
						break;				
					default:
						throw new IllegalStateException(
								"Unrecognized exit type descriptor: '" + this.exitTypeDescriptor + "'");
				}
			}
		}
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
