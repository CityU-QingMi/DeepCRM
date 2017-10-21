		@Override
		public Size apply(
				MethodVisitor methodVisitor,
				Implementation.Context implementationContext,
				MethodDescription instrumentedMethod) {
			methodVisitor.visitLdcInsn( getters.length );
			methodVisitor.visitTypeInsn( Opcodes.ANEWARRAY, Type.getInternalName( Object.class ) );
			int index = 0;
			for ( Method getter : getters ) {
				methodVisitor.visitInsn( Opcodes.DUP );
				methodVisitor.visitLdcInsn( index++ );
				methodVisitor.visitVarInsn( Opcodes.ALOAD, 1 );
				methodVisitor.visitTypeInsn( Opcodes.CHECKCAST, Type.getInternalName( clazz ) );
				methodVisitor.visitMethodInsn(
						Opcodes.INVOKEVIRTUAL,
						Type.getInternalName( clazz ),
						getter.getName(),
						Type.getMethodDescriptor( getter ),
						false
				);
				if ( getter.getReturnType().isPrimitive() ) {
					PrimitiveBoxingDelegate.forPrimitive( new TypeDescription.ForLoadedType( getter.getReturnType() ) )
							.assignBoxedTo(
									TypeDescription.Generic.OBJECT,
									ReferenceTypeAwareAssigner.INSTANCE,
									Assigner.Typing.STATIC
							)
							.apply( methodVisitor, implementationContext );
				}
				methodVisitor.visitInsn( Opcodes.AASTORE );
			}
			methodVisitor.visitInsn( Opcodes.ARETURN );
			return new Size( 6, instrumentedMethod.getStackSize() );
		}
