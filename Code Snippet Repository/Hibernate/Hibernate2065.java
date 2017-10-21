	public static boolean seedVersion(
			Object[] fields,
			int versionProperty,
			VersionType versionType,
			SharedSessionContractImplementor session) {
		final Object initialVersion = fields[versionProperty];
		if (
			initialVersion==null ||
			// This next bit is to allow for both unsaved-value="negative"
			// and for "older" behavior where version number did not get
			// seeded if it was already set in the object
			// TODO: shift it into unsaved-value strategy
			( (initialVersion instanceof Number) && ( (Number) initialVersion ).longValue()<0 )
		) {
			fields[versionProperty] = seed( versionType, session );
			return true;
		}
		LOG.tracev( "Using initial version: {0}", initialVersion );
		return false;
	}
