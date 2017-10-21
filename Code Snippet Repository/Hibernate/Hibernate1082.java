	private static void addWithModifiers(CtClass target, CtClass type, String  name, int modifiers, Class<?> ... annotations ) {
		try {
			final CtField f = new CtField( type, name, target );
			f.setModifiers( f.getModifiers() | modifiers );
			addAnnotations( f.getFieldInfo(), annotations );
			target.addField( f );
		}
		catch (CannotCompileException cce) {
			final String msg = String.format( "Could not enhance class [%s] to add field [%s]", target.getName(), name );
			throw new EnhancementException( msg, cce );
		}
	}
