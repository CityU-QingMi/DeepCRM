	@Override
	public boolean supportsSequences() {
		// technically sequence support was added in 10.6.1.0...
		//
		// The problem though is that I am not exactly sure how to differentiate 10.6.1.0 from any other 10.6.x release.
		//
		// http://db.apache.org/derby/docs/10.0/publishedapi/org/apache/derby/tools/sysinfo.html seems incorrect.  It
		// states that derby's versioning scheme is major.minor.maintenance, but obviously 10.6.1.0 has 4 components
		// to it, not 3.
		//
		// Let alone the fact that it states that versions with the matching major.minor are 'feature
		// compatible' which is clearly not the case here (sequence support is a new feature...)
		return driverVersionMajor > 10 || ( driverVersionMajor == 10 && driverVersionMinor >= 6 );
	}
