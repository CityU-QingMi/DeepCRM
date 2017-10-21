	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		if (this.name.equals(ROOT)) {
			mv.visitVarInsn(ALOAD,1);
		}
		else {
			mv.visitVarInsn(ALOAD, 2);
			mv.visitLdcInsn(name);
			mv.visitMethodInsn(INVOKEINTERFACE, "org/springframework/expression/EvaluationContext", "lookupVariable", "(Ljava/lang/String;)Ljava/lang/Object;",true);
		}
		CodeFlow.insertCheckCast(mv, this.exitTypeDescriptor);
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
