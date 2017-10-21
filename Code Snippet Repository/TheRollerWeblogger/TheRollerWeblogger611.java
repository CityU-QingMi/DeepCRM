    public Theme getTheme() {
        
        if(theme == null && themeName != null) {
            try {
                ThemeManager themeMgr = WebloggerFactory.getWeblogger().getThemeManager();
                theme = themeMgr.getTheme(themeName);
            } catch(ThemeNotFoundException tnfe) {
                // bogus theme specified ... don't worry about it
            } catch(WebloggerException re) {
                log.error("Error looking up theme "+themeName, re);
            }
        }
        
        return theme;
    }
