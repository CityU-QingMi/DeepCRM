	@Override
	@SuppressWarnings("")
	public <T> T cast(Class<T> expected, Object candidate){
		if (candidate == null) {
			return null;
		}

		if ( expected.isInstance( candidate ) ) {
			return (T) candidate;
		}

		Class<T> target;
		if ( Class.class.isInstance( candidate ) ) {
			target = Class.class.cast( candidate );
		}
		else {
			try {
				target = serviceRegistry.getService( ClassLoaderService.class ).classForName( candidate.toString() );
			}
			catch ( ClassLoadingException e ) {
				LOG.debugf( "Unable to locate %s implementation class %s", expected.getName(), candidate.toString() );
				target = null;
			}
		}
		if ( target != null ) {
			try {
				return target.newInstance();
			}
			catch ( Exception e ) {
				LOG.debugf(
						"Unable to instantiate %s class %s", expected.getName(),
						target.getName()
				);
			}
		}
		return null;
	}
