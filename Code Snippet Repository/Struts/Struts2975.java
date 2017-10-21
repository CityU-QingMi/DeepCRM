    private void init() throws JasperException {
        if (initialized) return;
        try {
            processWebDotXml();
            scanJars();
            processTldsInFileSystem("/WEB-INF/");
            initialized = true;
        } catch (Exception ex) {
            throw new JasperException(Localizer.getMessage(
                    "jsp.error.internal.tldinit", ex.getMessage()));
        }
    }
