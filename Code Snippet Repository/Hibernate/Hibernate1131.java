	BulkAccessor create() {
		final Method[] getters = new Method[getterNames.length];
		final Method[] setters = new Method[setterNames.length];
		findAccessors( targetBean, getterNames, setterNames, types, getters, setters );

		final Class beanClass;
		try {
			final ClassFile classfile = make( getters, setters );
			final ClassLoader loader = this.getClassLoader();
			if ( writeDirectory != null ) {
				FactoryHelper.writeFile( classfile, writeDirectory );
			}

			beanClass = FactoryHelper.toClass( classfile, loader, getDomain() );
			return (BulkAccessor) this.newInstance( beanClass );
		}
		catch ( Exception e ) {
			throw new BulkAccessorException( e.getMessage(), e );
		}
	}
