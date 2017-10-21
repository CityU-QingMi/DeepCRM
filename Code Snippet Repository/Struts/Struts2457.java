    protected String determinePath(ActionConfig actionConfig, String namespace) {
        String finalPrefix = conventionsService.determineResultPath(actionConfig);

        if (!finalPrefix.endsWith("/")) {
            finalPrefix += "/";
        }

        if (StringUtils.equals(namespace,"/" )) {
            namespace = "";
        }

        if (namespace.length() > 0) {
            if (namespace.startsWith("/")) {
                namespace = namespace.substring(1);
            }

            if (!namespace.endsWith("/")) {
                namespace += "/";
            }
        }

        return finalPrefix + namespace;
    }
