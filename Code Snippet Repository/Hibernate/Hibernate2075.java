	private boolean isFirstArgColumnLabel(Method method, Object[] args) {
		// method name should start with either get or update
		if ( ! ( method.getName().startsWith( "get" ) || method.getName().startsWith( "update" ) ) ) {
			return false;
		}

		// method should have arguments, and have same number as incoming arguments
		if ( ! ( method.getParameterCount() > 0 && args.length == method.getParameterCount() ) ) {
			return false;
		}

		// The first argument should be a String (the column name)
		//noinspection RedundantIfStatement
		if ( ! ( String.class.isInstance( args[0] ) && method.getParameterTypes()[0].equals( String.class ) ) ) {
			return false;
		}

		return true;
	}
