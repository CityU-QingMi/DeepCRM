	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		Integer intValue = (Integer) this.value.getValue();
		Assert.state(intValue != null, "No int value");
		if (intValue == -1) {
			// Not sure we can get here because -1 is OpMinus
			mv.visitInsn(ICONST_M1);
		}
		else if (intValue >= 0 && intValue < 6) {
			mv.visitInsn(ICONST_0 + intValue);
		}
		else {
			mv.visitLdcInsn(intValue);
		}
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
