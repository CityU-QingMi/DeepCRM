	private Map<String, SharedTheme> loadAllThemesFromDisk() {

		Map<String, SharedTheme> themeMap = new HashMap<String, SharedTheme>();

		// first, get a list of the themes available
		File themesdir = new File(this.themeDir);
		FilenameFilter filter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				File file = new File(dir.getAbsolutePath() + File.separator
						+ name);
				return file.isDirectory() && !file.getName().startsWith(".");
			}
		};
		String[] themenames = themesdir.list(filter);

		if (themenames == null) {
			log.warn("No themes found!  Perhaps wrong directory for themes specified?  "
					+ "(Check themes.dir setting in roller[-custom].properties file.)");
		} else {
            log.info("Loading themes from " + themesdir.getAbsolutePath() + "...");

            // now go through each theme and load it into a Theme object
            for (String themeName : themenames) {
                try {
                    SharedTheme theme = new SharedThemeFromDir(this.themeDir
                            + File.separator + themeName);
                    themeMap.put(theme.getId(), theme);
                    log.info("Loaded theme '" + themeName + "'");
                } catch (Exception unexpected) {
                    // shouldn't happen, so let's learn why it did
                    log.error("Problem processing theme '" + themeName + "':", unexpected);
                }
            }
        }

		return themeMap;
	}
