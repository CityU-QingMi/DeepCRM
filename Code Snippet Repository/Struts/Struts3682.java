    public static File getViewFile(String namespace, String actionName, String resultName) {
        ResultConfig result = getResultConfig(namespace, actionName, resultName);
        String location = result.getParams().get("location");
        for (String viewRoot : views) {
            File viewFile = getViewFileInternal(viewRoot, location, namespace);
            if (viewFile != null) {
                return viewFile;
            }
        }

        return null;
    }
