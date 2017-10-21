	@Override
	public synchronized byte[] enhance(String className, byte[] originalBytes) throws EnhancementException {
		CtClass managedCtClass = null;
		try {
			managedCtClass = classPool.makeClassIfNew( new ByteArrayInputStream( originalBytes ) );
			return enhance( managedCtClass ) ? getByteCode( managedCtClass ) : null;
		}
		catch (IOException e) {
			log.unableToBuildEnhancementMetamodel( className );
			return null;
		}
		finally {
			if ( managedCtClass != null ) {
				managedCtClass.detach();
			}
		}
	}
