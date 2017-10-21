    @Inject(value = "", required = false)
    public void setThemeClasses(String themeClasses) {
        StringTokenizer customThemes = new StringTokenizer(themeClasses, ",");
        while (customThemes.hasMoreTokens()) {
            String themeClass = customThemes.nextToken().trim();
            try {
                LOG.info("Registering custom theme [{}] to javatemplates engine", themeClass);
                ObjectFactory factory = ActionContext.getContext().getContainer().getInstance(ObjectFactory.class);
                Theme theme = (Theme) factory.buildBean(themeClass, new HashMap<String, Object>());
                themes.add(theme);
            } catch (ClassCastException cce) {
                LOG.error("Invalid java them class [{}]. Class does not implement 'org.apache.struts2.views.java.Theme' interface", themeClass, cce);
            } catch (ClassNotFoundException cnf) {
                LOG.error("Invalid java theme class [{}]. Class not found!", themeClass, cnf);
            } catch (Exception e) {
                LOG.error("Could not find messages file [{}].properties. Skipping!", themeClass, e);
            }
        }
    }
