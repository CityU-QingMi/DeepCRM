    @Override
    public String getConfigLocationUri() {
        if (loggerContext.getConfigLocation() != null) {
            return String.valueOf(loggerContext.getConfigLocation());
        }
        if (getConfigName() != null) {
            return String.valueOf(new File(getConfigName()).toURI());
        }
        return Strings.EMPTY;
    }
