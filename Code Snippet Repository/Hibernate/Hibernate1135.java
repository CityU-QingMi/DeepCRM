	private void addDefaultConstructor(ClassFile classfile) throws CannotCompileException {
		final ConstPool constPool = classfile.getConstPool();
		final String constructorSignature = "()V";
		final MethodInfo constructorMethodInfo = new MethodInfo( constPool, MethodInfo.nameInit, constructorSignature );

		final Bytecode code = new Bytecode( constPool, 0, 1 );
		// aload_0
		code.addAload( 0 );
		// invokespecial
		code.addInvokespecial( BulkAccessor.class.getName(), MethodInfo.nameInit, constructorSignature );
		// return
		code.addOpcode( Opcode.RETURN );

		constructorMethodInfo.setCodeAttribute( code.toCodeAttribute() );
		constructorMethodInfo.setAccessFlags( AccessFlag.PUBLIC );
		classfile.addMethod( constructorMethodInfo );
	}
