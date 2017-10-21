    public void parseValidatorDefinitions(Map<String, String> validators, InputStream is, String resourceName) {

        InputSource in = new InputSource(is);
        in.setSystemId(resourceName);

        Map<String, String> dtdMappings = new HashMap<>();
        dtdMappings.put("-//Apache Struts//XWork Validator Config 1.0//EN", "xwork-validator-config-1.0.dtd");
        dtdMappings.put("-//Apache Struts//XWork Validator Definition 1.0//EN", "xwork-validator-definition-1.0.dtd");

        Document doc = DomHelper.parse(in, dtdMappings);

        if (doc != null) {
            NodeList nodes = doc.getElementsByTagName("validator");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element validatorElement = (Element) nodes.item(i);
                String name = validatorElement.getAttribute("name");
                String className = validatorElement.getAttribute("class");

                try {
                    // catch any problems here
                    objectFactory.buildValidator(className, new HashMap<String, Object>(), ActionContext.getContext().getContextMap());
                    validators.put(name, className);
                } catch (Exception e) {
                    throw new ConfigurationException("Unable to load validator class " + className, e, validatorElement);
                }
            }
        }
    }
