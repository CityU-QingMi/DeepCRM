		@Override
		public void generateCode(String propertyName, MethodVisitor mv, CodeFlow cf) {
			boolean isStatic = Modifier.isStatic(this.member.getModifiers());
			String descriptor = cf.lastDescriptor();
			String classDesc = this.member.getDeclaringClass().getName().replace('.', '/');

			if (!isStatic) {
				if (descriptor == null) {
					cf.loadTarget(mv);
				}
				if (descriptor == null || !classDesc.equals(descriptor.substring(1))) {
					mv.visitTypeInsn(CHECKCAST, classDesc);
				}
			}
			else {
				if (descriptor != null) {
					// A static field/method call will not consume what is on the stack,
					// it needs to be popped off.
					mv.visitInsn(POP);
				}
			}

			if (this.member instanceof Method) {
				mv.visitMethodInsn((isStatic ? INVOKESTATIC : INVOKEVIRTUAL), classDesc, this.member.getName(),
						CodeFlow.createSignatureDescriptor((Method) this.member), false);
			}
			else {
				mv.visitFieldInsn((isStatic ? GETSTATIC : GETFIELD), classDesc, this.member.getName(),
						CodeFlow.toJvmDescriptor(((Field) this.member).getType()));
			}
		}
