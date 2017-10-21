	public WeblogTheme getTheme(Weblog weblog) throws WebloggerException {

		if (weblog == null) {
			return null;
		}

		WeblogTheme weblogTheme = null;

		// if theme is custom or null then return a WeblogCustomTheme
		if (weblog.getEditorTheme() == null
				|| WeblogTheme.CUSTOM.equals(weblog.getEditorTheme())) {
			weblogTheme = new WeblogCustomTheme(weblog);

			// otherwise we are returning a WeblogSharedTheme
		} else {
			SharedTheme staticTheme = (SharedTheme) this.themes.get(weblog
					.getEditorTheme());
			if (staticTheme != null) {
				weblogTheme = new WeblogSharedTheme(weblog, staticTheme);
			} else {
				log.warn("Unable to lookup theme " + weblog.getEditorTheme());
			}
		}

		// TODO: if somehow the theme is still null should we provide some
		// kind of fallback option like a default theme?

		return weblogTheme;
	}
