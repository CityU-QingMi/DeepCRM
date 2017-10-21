	@Configuration
	public Option[] config() {
		return options(mavenBundle("org.quartz-scheduler", "quartz")
				.versionAsInProject(), wrappedBundle(maven("c3p0", "c3p0")
				.versionAsInProject()),
        mavenBundle("org.slf4j", "slf4j-api").versionAsInProject(),
        mavenBundle("org.slf4j", "slf4j-log4j12").versionAsInProject().noStart(),
        mavenBundle("log4j", "log4j").versionAsInProject().noStart(),        
        OsgiUtil.commonOptions());
	}
