	public boolean reLoadThemeFromDisk(String reloadTheme) {

		boolean reloaded = false;

		try {

            SharedTheme theme = new SharedThemeFromDir(this.themeDir + File.separator
					+ reloadTheme);

            Theme loadedTheme = themes.get(theme.getId());

            if (loadedTheme != null
                    && theme.getLastModified().after(
                            loadedTheme.getLastModified())) {
                themes.remove(theme.getId());
                themes.put(theme.getId(), theme);
                reloaded = true;
            }

		} catch (Exception unexpected) {
			// shouldn't happen, so let's learn why it did
			log.error("Problem reloading theme " + reloadTheme, unexpected);
		}

		return reloaded;

	}
