    public List<ValidatorConfig> parseActionValidatorConfigs(ValidatorFactory validatorFactory, InputStream is, final String resourceName) {
        List<ValidatorConfig> validatorCfgs = new ArrayList<>();

        InputSource in = new InputSource(is);
        in.setSystemId(resourceName);

        Map<String, String> dtdMappings = new HashMap<>();
        dtdMappings.put("-//Apache Struts//XWork Validator 1.0//EN", "xwork-validator-1.0.dtd");
        dtdMappings.put("-//Apache Struts//XWork Validator 1.0.2//EN", "xwork-validator-1.0.2.dtd");
        dtdMappings.put("-//Apache Struts//XWork Validator 1.0.3//EN", "xwork-validator-1.0.3.dtd");
        dtdMappings.put("-//Apache Struts//XWork Validator Config 1.0//EN", "xwork-validator-config-1.0.dtd");

        Document doc = DomHelper.parse(in, dtdMappings);

        if (doc != null) {
            NodeList fieldNodes = doc.getElementsByTagName("field");

            // BUG: xw-305: Let validator be parsed first and hence added to 
            // the beginning of list and therefore evaluated first, so short-circuting
            // it will not cause field-level validator to be kicked off.
            {
                NodeList validatorNodes = doc.getElementsByTagName("validator");
                addValidatorConfigs(validatorFactory, validatorNodes, new HashMap<String, Object>(), validatorCfgs);
            }

            for (int i = 0; i < fieldNodes.getLength(); i++) {
                Element fieldElement = (Element) fieldNodes.item(i);
                String fieldName = fieldElement.getAttribute("name");
                Map<String, Object> extraParams = new HashMap<String, Object>();
                extraParams.put("fieldName", fieldName);

                NodeList validatorNodes = fieldElement.getElementsByTagName("field-validator");
                addValidatorConfigs(validatorFactory, validatorNodes, extraParams, validatorCfgs);
            }
        }

        return validatorCfgs;
    }
