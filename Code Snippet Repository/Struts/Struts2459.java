    public String determineResultPath(ActionConfig actionConfig) {
        if (actionConfig == null) {
            return resultPath;
        }

        try {
            return  determineResultPath(ClassLoaderUtil.loadClass(actionConfig.getClassName(), this.getClass()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Invalid action class configuration that references an unknown " +
                "class named [" + actionConfig.getClassName() + "]", e);
        }
    }
