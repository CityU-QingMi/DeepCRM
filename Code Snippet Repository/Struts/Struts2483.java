        String determineType(String location, PackageConfig packageConfig,
                Map<String, ResultTypeConfig> resultsByExtension) {
            int indexOfDot = location.lastIndexOf(".");
            if (indexOfDot > 0) {
                String extension = location.substring(indexOfDot + 1);
                ResultTypeConfig resultTypeConfig = resultsByExtension.get(extension);
                if (resultTypeConfig != null) {
                    return resultTypeConfig.getName();
                } else
                    throw new ConfigurationException("Unable to find a result type for extension [" + extension + "] " +
                    		"in location attribute [" + location + "].");
            } else {
                return packageConfig.getFullDefaultResultType();
            }
        }
