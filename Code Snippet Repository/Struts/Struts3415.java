    public URL loadResource(String name, boolean translate) {
        Bundle bundle = getCurrentBundle();
        if (bundle != null) {
            URL url = bundle.getResource(name);
            try {
                return translate ? OsgiUtil.translateBundleURLToJarURL(url, getCurrentBundle()) : url;
            } catch (Exception e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error("Unable to translate bundle URL to jar URL", e);
                }

                return null;
            }
        }

        return null;
    }
