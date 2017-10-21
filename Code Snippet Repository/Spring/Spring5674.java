	@Override
	public void generateCode(MethodVisitor mv, CodeFlow cf) {
		CachedMethodExecutor executorToCheck = this.cachedExecutor;
		if (executorToCheck == null || !(executorToCheck.get() instanceof ReflectiveMethodExecutor)) {
			throw new IllegalStateException("No applicable cached executor found: " + executorToCheck);
		}

		ReflectiveMethodExecutor methodExecutor = (ReflectiveMethodExecutor) executorToCheck.get();
		Method method = methodExecutor.getMethod();
		boolean isStaticMethod = Modifier.isStatic(method.getModifiers());
		String descriptor = cf.lastDescriptor();

		if (descriptor == null) {
			if (!isStaticMethod) {
				// Nothing on the stack but something is needed
				cf.loadTarget(mv);
			}
		}
		else {
			if (isStaticMethod) {
				// Something on the stack when nothing is needed
				mv.visitInsn(POP);
			}
		}
		
		if (CodeFlow.isPrimitive(descriptor)) {
			CodeFlow.insertBoxIfNecessary(mv, descriptor.charAt(0));
		}

		String classDesc = null;
		if (Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			classDesc = method.getDeclaringClass().getName().replace('.', '/');
		}
		else {
			Class<?> publicDeclaringClass = methodExecutor.getPublicDeclaringClass();
			Assert.state(publicDeclaringClass != null, "No public declaring class");
			classDesc = publicDeclaringClass.getName().replace('.', '/');
		};

		if (!isStaticMethod) {
			if (descriptor == null || !descriptor.substring(1).equals(classDesc)) {
				CodeFlow.insertCheckCast(mv, "L" + classDesc);
			}
		}

		generateCodeForArguments(mv, cf, method, this.children);
		mv.visitMethodInsn((isStaticMethod ? INVOKESTATIC : INVOKEVIRTUAL), classDesc, method.getName(),
				CodeFlow.createSignatureDescriptor(method), method.getDeclaringClass().isInterface());
		cf.pushDescriptor(this.exitTypeDescriptor);
	}
