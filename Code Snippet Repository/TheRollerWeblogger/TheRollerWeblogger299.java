	public SharedTheme getTheme(String id) throws WebloggerException {

		// try to lookup theme from library
		SharedTheme theme = this.themes.get(id);

		// no theme? throw exception.
		if (theme == null) {
			throw new ThemeNotFoundException("Couldn't find theme [" + id + "]");
		}

		return theme;
	}
