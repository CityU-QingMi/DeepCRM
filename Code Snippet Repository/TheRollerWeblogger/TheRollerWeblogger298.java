	public void initialize() throws InitializationException {

		log.debug("Initializing Theme Manager");

		if (themeDir != null) {
			// rather than be lazy we are going to load all themes from
			// the disk preemptive and cache them
			this.themes = loadAllThemesFromDisk();

			log.info("Successfully loaded " + this.themes.size() + " themes from disk.");
		}
	}
