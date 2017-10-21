	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		ReflectiveConstructorExecutor executor = ((ReflectiveConstructorExecutor) this.cachedExecutor);
		Assert.state(executor != null, "No cached executor");

		Constructor<?> constructor = executor.getConstructor();
		String classDesc = constructor.getDeclaringClass().getName().replace('.', '/');
		mv.visitTypeInsn(NEW, classDesc);
		mv.visitInsn(DUP);

		// children[0] is the type of the constructor, don't want to include that in argument processing
		SpelNodeImpl[] arguments = new SpelNodeImpl[children.length - 1];
		System.arraycopy(children, 1, arguments, 0, children.length - 1);
		generateCodeForArguments(mv, cf, constructor, arguments);	
		mv.visitMethodInsn(INVOKESPECIAL, classDesc, "<init>", CodeFlow.createSignatureDescriptor(constructor), false);
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
