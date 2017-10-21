    public String determineResultPath(Class<?> actionClass) {
        String localResultPath = resultPath;
        ResultPath resultPathAnnotation = AnnotationUtils.findAnnotation(actionClass, ResultPath.class);
        if (resultPathAnnotation != null) {
            if (resultPathAnnotation.value().equals("") && resultPathAnnotation.property().equals("")) {
                throw new ConfigurationException("The ResultPath annotation must have either" +
                    " a value or property specified.");
            }

            String property = resultPathAnnotation.property();
            if (property.equals("")) {
                localResultPath = resultPathAnnotation.value();
            } else {
                try {
                    ResourceBundle strutsBundle = ResourceBundle.getBundle("struts");
                    localResultPath = strutsBundle.getString(property);
                } catch (Exception e) {
                    throw new ConfigurationException("The action class [" + actionClass + "] defines" +
                        " a @ResultPath annotation and a property definition however the" +
                        " struts.properties could not be found in the classpath using ResourceBundle" +
                        " OR the bundle exists but the property [" + property + "] is not defined" +
                        " in the file.", e);
                }
            }
        }

        return localResultPath;
    }
