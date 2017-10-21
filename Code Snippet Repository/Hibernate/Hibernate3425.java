	@Override
	public byte[] transform(
			ClassLoader loader,
			String className,
			Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {

		// The first design had the enhancer as a class variable. That approach had some goods and bads.
		// We don't had to create an enhancer for each class, but on the other end it would stay in memory forever.
		// It also assumed that all calls come from the same class loader, which is fair, but this makes it more robust.

		try {
			Enhancer enhancer = Environment.getBytecodeProvider().getEnhancer( new EnhancementContextWrapper( enhancementContext, loader ) );
			return enhancer.enhance( className, classfileBuffer );
		}
		catch (final Exception e) {
			throw new IllegalClassFormatException( "Error performing enhancement of " + className ) {
				@Override
				public synchronized Throwable getCause() {
					return e;
				}
			};
		}
	}
