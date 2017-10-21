	private void internalTest(ThemeResolver themeResolver, boolean shouldSet, String defaultName) {
		// create mocks
		MockServletContext context = new MockServletContext();
		MockHttpServletRequest request = new MockHttpServletRequest(context);
		MockHttpServletResponse response = new MockHttpServletResponse();
		// check original theme
		String themeName = themeResolver.resolveThemeName(request);
		assertEquals(themeName, defaultName);
		// set new theme name
		try {
			themeResolver.setThemeName(request, response, TEST_THEME_NAME);
			if (!shouldSet)
				fail("should not be able to set Theme name");
			// check new theme namelocale
			themeName = themeResolver.resolveThemeName(request);
			assertEquals(TEST_THEME_NAME, themeName);
			themeResolver.setThemeName(request, response, null);
			themeName = themeResolver.resolveThemeName(request);
			assertEquals(themeName, defaultName);
		}
		catch (UnsupportedOperationException ex) {
			if (shouldSet)
				fail("should be able to set Theme name");
		}
	}
