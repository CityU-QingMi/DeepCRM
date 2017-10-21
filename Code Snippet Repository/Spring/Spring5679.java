	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		cf.loadEvaluationContext(mv);
		String leftDesc = getLeftOperand().exitTypeDescriptor;
		String rightDesc = getRightOperand().exitTypeDescriptor;
		boolean leftPrim = CodeFlow.isPrimitive(leftDesc);
		boolean rightPrim = CodeFlow.isPrimitive(rightDesc);

		cf.enterCompilationScope();
		getLeftOperand().generateCode(mv, cf);
		cf.exitCompilationScope();
		if (leftPrim) {
			CodeFlow.insertBoxIfNecessary(mv, leftDesc.charAt(0));
		}
		cf.enterCompilationScope();
		getRightOperand().generateCode(mv, cf);
		cf.exitCompilationScope();
		if (rightPrim) {
			CodeFlow.insertBoxIfNecessary(mv, rightDesc.charAt(0));
		}

		String operatorClassName = Operator.class.getName().replace('.', '/');
		String evaluationContextClassName = EvaluationContext.class.getName().replace('.', '/');
		mv.visitMethodInsn(INVOKESTATIC, operatorClassName, "equalityCheck",
				"(L" + evaluationContextClassName + ";Ljava/lang/Object;Ljava/lang/Object;)Z", false);
		cf.pushDescriptor("Z");
	}
