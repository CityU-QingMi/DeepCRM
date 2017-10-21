	private void walk(MethodVisitor mv, CodeFlow cf, @Nullable SpelNodeImpl operand) {
		if (operand instanceof OpPlus) {
			OpPlus plus = (OpPlus)operand;
			walk(mv, cf, plus.getLeftOperand());
			walk(mv, cf, plus.getRightOperand());
		}
		else if (operand != null) {
			cf.enterCompilationScope();
			operand.generateCode(mv,cf);
			if (!"Ljava/lang/String".equals(cf.lastDescriptor())) {
				mv.visitTypeInsn(CHECKCAST, "java/lang/String");
			}
			cf.exitCompilationScope();
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		}
	}
