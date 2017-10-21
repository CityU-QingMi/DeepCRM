	private static BytecodeProvider buildBytecodeProvider(String providerName) {
		if ( BYTECODE_PROVIDER_NAME_BYTEBUDDY.equals( providerName ) ) {
			return new org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl();
		}

		if ( BYTECODE_PROVIDER_NAME_JAVASSIST.equals( providerName ) ) {
			return new org.hibernate.bytecode.internal.javassist.BytecodeProviderImpl();
		}

		LOG.bytecodeProvider( providerName );

		// todo : allow a custom class name - just check if the config is a FQN
		//		currently we assume it is only ever the Strings "javassist" or "bytebuddy"...

		LOG.unknownBytecodeProvider( providerName, BYTECODE_PROVIDER_NAME_DEFAULT );
		return new org.hibernate.bytecode.internal.javassist.BytecodeProviderImpl();
	}
