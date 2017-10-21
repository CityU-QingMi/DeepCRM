		@Override
		public Size apply(
				MethodVisitor methodVisitor,
				Implementation.Context implementationContext,
				MethodDescription instrumentedMethod) {
			int index = 0;
			for ( Method setter : setters ) {
				methodVisitor.visitVarInsn( Opcodes.ALOAD, 1 );
				methodVisitor.visitTypeInsn( Opcodes.CHECKCAST, Type.getInternalName( clazz ) );
				methodVisitor.visitVarInsn( Opcodes.ALOAD, 2 );
				methodVisitor.visitLdcInsn( index++ );
				methodVisitor.visitInsn( Opcodes.AALOAD );
				if ( setter.getParameterTypes()[0].isPrimitive() ) {
					PrimitiveUnboxingDelegate.forReferenceType( TypeDescription.Generic.OBJECT )
							.assignUnboxedTo(
									new TypeDescription.Generic.OfNonGenericType.ForLoadedType( setter.getParameterTypes()[0] ),
									ReferenceTypeAwareAssigner.INSTANCE,
									Assigner.Typing.DYNAMIC
							)
							.apply( methodVisitor, implementationContext );
				}
				else {
					methodVisitor.visitTypeInsn( Opcodes.CHECKCAST, Type.getInternalName( setter.getParameterTypes()[0] ) );
				}
				methodVisitor.visitMethodInsn(
						Opcodes.INVOKEVIRTUAL,
						Type.getInternalName( clazz ),
						setter.getName(),
						Type.getMethodDescriptor( setter ),
						false
				);
			}
			methodVisitor.visitInsn( Opcodes.RETURN );
			return new Size( 4, instrumentedMethod.getStackSize() );
		}
