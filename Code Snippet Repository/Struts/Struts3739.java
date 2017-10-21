    public ApplicationResource getResource(ApplicationResource base, Locale locale) {
        String localePath = base.getLocalePath(locale);
        File localFile = new File(localePath);
        if (localFile.exists()) {
            try {
                return new StrutsApplicationResource(localFile.toURI().toURL());
            } catch (MalformedURLException e) {
                LOG.warn("Cannot access [{}]", localePath, e);
                return null;
            }
        }
        return null;
    }
