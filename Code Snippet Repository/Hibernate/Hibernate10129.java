	protected static Class generate(String className, Map<String, Class<?>> properties) {
		try {
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.makeClass( className );

			cc.addInterface( resolveCtClass( Serializable.class ) );
			cc.addField( new CtField( resolveCtClass( Map.class ), "theMap", cc ) );
			cc.addConstructor( generateConstructor( className, cc ) );

			for ( Entry<String, Class<?>> entry : properties.entrySet() ) {

				// add getter
				cc.addMethod( generateGetter( cc, entry.getKey(), entry.getValue() ) );

				// add setter
				cc.addMethod( generateSetter( cc, entry.getKey(), entry.getValue() ) );
			}
			return cc.toClass();
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}
	}
