    protected Version getFreemarkerVersion(ServletContext servletContext) {
        Version incompatibleImprovements = Configuration.VERSION_2_3_0;

        String incompatibleImprovementsParam = servletContext.getInitParameter("freemarker." + Configuration.INCOMPATIBLE_IMPROVEMENTS_KEY_SNAKE_CASE);
        if (incompatibleImprovementsParam != null) {
            incompatibleImprovements = new Version(incompatibleImprovementsParam);
        }

        return incompatibleImprovements;
    }
