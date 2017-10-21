    protected void makeResults(Class<?> actionClass, String path, String resultPrefix,
            Map<String, ResultConfig> results, PackageConfig packageConfig,
            Map<String, ResultTypeConfig> resultsByExtension) {

        if (path.startsWith(resultPrefix)) {
            int indexOfDot = path.indexOf('.', resultPrefix.length());

            // This case is when the path doesn't contain a result code
            if (indexOfDot == resultPrefix.length()) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("The result file [{}] has no result code and therefore" +
                        " will be associated with success, input and error by default. This might" +
                        " be overridden by another result file or an annotation.", path);
                }

                addResult(actionClass, path, results, packageConfig, resultsByExtension, Action.SUCCESS);
                addResult(actionClass, path, results, packageConfig, resultsByExtension, Action.INPUT);
                addResult(actionClass, path, results, packageConfig, resultsByExtension, Action.ERROR);

            // This case is when the path contains a result code
            } else if (indexOfDot > resultPrefix.length()) {
                if (LOG.isTraceEnabled()) {
                    LOG.trace("The result file [{}] has a result code and therefore" +
                        " will be associated with only that result code.", path);
                }

                String resultCode = path.substring(resultPrefix.length() + 1, indexOfDot);
                ResultConfig result = createResultConfig(actionClass,
                    new ResultInfo(resultCode, path, packageConfig, resultsByExtension),
                    packageConfig, null);
                results.put(resultCode, result);
            }
        }
    }
