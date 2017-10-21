	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		getLeftOperand().generateCode(mv, cf);
		CodeFlow.insertBoxIfNecessary(mv, cf.lastDescriptor());
		Assert.state(this.type != null, "No type available");
		if (this.type.isPrimitive()) {
			// always false - but left operand code always driven
			// in case it had side effects
			mv.visitInsn(POP);
			mv.visitInsn(ICONST_0); // value of false
		} 
		else {
			mv.visitTypeInsn(INSTANCEOF, Type.getInternalName(this.type));
		}
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
