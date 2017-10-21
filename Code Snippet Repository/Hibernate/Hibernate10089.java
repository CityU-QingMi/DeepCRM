	@Override
	public Object generate() {
		Object revisionInfo;
		try {
			revisionInfo = ReflectHelper.getDefaultConstructor( revisionInfoClass ).newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException( e );
		}

		final long timestamp = System.currentTimeMillis();
		revisionTimestampSetter.set( revisionInfo, timestampAsDate ? new Date( timestamp ) : timestamp, null );

		if ( listener != null ) {
			listener.newRevision( revisionInfo );
		}

		return revisionInfo;
	}
