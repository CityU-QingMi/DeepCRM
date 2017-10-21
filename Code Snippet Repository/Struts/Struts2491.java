        public ResultInfo(String name, Result result, PackageConfig packageConfig,
                String resultPath, Class<?> actionClass,
                Map<String, ResultTypeConfig> resultsByExtension) {
            this.name = name;
            if (StringUtils.isNotBlank(result.type())) {
                this.type = result.type();
            } else if (StringUtils.isNotBlank(result.location())) {
                this.type = determineType(result.location(), packageConfig, resultsByExtension);
            } else {
                throw new ConfigurationException("The action class [" + actionClass + "] contains a " +
                    "result annotation that has no type parameter and no location parameter. One of " +
                    "these must be defined.");
            }

            // See if we can handle relative locations or not
            if (StringUtils.isNotBlank(result.location())) {
                if (relativeResultTypes.contains(this.type) && !result.location().startsWith("/")) {
                    location = resultPath + result.location();
                } else {
                    location = result.location();
                }
            } else {
                this.location = null;
            }
        }
