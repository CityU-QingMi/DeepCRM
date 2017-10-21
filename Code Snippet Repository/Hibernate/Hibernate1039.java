	@Override
	public synchronized byte[] enhance(String className, byte[] originalBytes) throws EnhancementException {
		try {
			final TypeDescription managedCtClass = classPool.describe( className ).resolve();
			DynamicType.Builder<?> builder = doEnhance(
					new ByteBuddy().ignore( isDefaultFinalizer() ).with( TypeValidation.DISABLED ).redefine( managedCtClass, ClassFileLocator.Simple.of( className, originalBytes ) ),
					managedCtClass
			);
			if ( builder == null ) {
				return null;
			}
			else {
				return builder.make().getBytes();
			}
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			log.unableToBuildEnhancementMetamodel( className );
			return null;
		}
	}
