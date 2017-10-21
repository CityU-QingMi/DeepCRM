	protected static void generateCodeForArgument(MethodVisitor mv, CodeFlow cf, SpelNodeImpl argument, String paramDesc) {
		cf.enterCompilationScope();
		argument.generateCode(mv, cf);
		String lastDesc = cf.lastDescriptor();
		Assert.state(lastDesc != null, "No last descriptor");
		boolean primitiveOnStack = CodeFlow.isPrimitive(lastDesc);
		// Check if need to box it for the method reference?
		if (primitiveOnStack && paramDesc.charAt(0) == 'L') {
			CodeFlow.insertBoxIfNecessary(mv, lastDesc.charAt(0));
		}
		else if (paramDesc.length() == 1 && !primitiveOnStack) {
			CodeFlow.insertUnboxInsns(mv, paramDesc.charAt(0), lastDesc);
		}
		else if (!paramDesc.equals(lastDesc)) {
			// This would be unnecessary in the case of subtyping (e.g. method takes Number but Integer passed in)
			CodeFlow.insertCheckCast(mv, paramDesc);
		}
		cf.exitCompilationScope();
	}
