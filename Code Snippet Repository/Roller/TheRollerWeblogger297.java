	@com.google.inject.Inject
	protected ThemeManagerImpl(Weblogger roller) {

		this.roller = roller;

		// get theme directory from config and verify it
		this.themeDir = WebloggerConfig.getProperty("themes.dir");
		if (themeDir == null || themeDir.trim().length() < 1) {
			throw new RuntimeException(
					"couldn't get themes directory from config");
		} else {
			// chop off trailing slash if it exists
			if (themeDir.endsWith("/")) {
				themeDir = themeDir.substring(0, themeDir.length() - 1);
			}

			// make sure it exists and is readable
			File themeDirFile = new File(themeDir);
			if (!themeDirFile.exists() || !themeDirFile.isDirectory()
					|| !themeDirFile.canRead()) {
				throw new RuntimeException("couldn't access theme dir ["
						+ themeDir + "]");
			}
		}
	}
