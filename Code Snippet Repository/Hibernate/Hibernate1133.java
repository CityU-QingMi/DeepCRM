	private ClassFile make(Method[] getters, Method[] setters) throws CannotCompileException {
		String className = targetBean.getName();
		// set the name of bulk accessor.
		className = className + "_$$_bulkaccess_" + counter++;
		if ( className.startsWith( "java." ) ) {
			className = PACKAGE_NAME_PREFIX + className;
		}

		final ClassFile classfile = new ClassFile( false, className, BULKACESSOR_CLASS_NAME );
		classfile.setAccessFlags( AccessFlag.PUBLIC );
		addDefaultConstructor( classfile );
		addGetter( classfile, getters );
		addSetter( classfile, setters );
		return classfile;
	}
