    public synchronized List<Configurer> getConfigurers(Class clazz, String context, boolean validateJPAAnnotations) {
        this.validateJPAAnnotations =validateJPAAnnotations;
        final String validatorKey = buildValidatorKey(clazz, context);

        if (validatorCache.containsKey(validatorKey)) {
            if (reloadConfigs) {
                List<Configurer> configurers = buildXMLConfigurers(clazz, context, true, null);

                //add an annotation configurer
                addAditionalConfigurers(configurers);
                validatorCache.put(validatorKey, configurers);
            }
        } else {
            List<Configurer> configurers = buildXMLConfigurers(clazz, context, false, null);

            //add an annotation configurer
            addAditionalConfigurers(configurers);
            validatorCache.put(validatorKey, configurers);
        }

        // get the set of validator configs
        return validatorCache.get(validatorKey);
    }
