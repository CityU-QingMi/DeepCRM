	public void finish() {
		if (this.fieldAdders != null) {
			for (FieldAdder fieldAdder : this.fieldAdders) {
				fieldAdder.generateField(this.classWriter, this);
			}
		}
		if (this.clinitAdders != null) {
			MethodVisitor mv = this.classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "<clinit>", "()V", null, null);
			mv.visitCode();
			this.nextFreeVariableId = 0;  // to 0 because there is no 'this' in a clinit
			for (ClinitAdder clinitAdder : this.clinitAdders) {
				clinitAdder.generateCode(mv, this);
			}
			mv.visitInsn(RETURN);
			mv.visitMaxs(0,0);  // not supplied due to COMPUTE_MAXS
			mv.visitEnd();
		}
	}
