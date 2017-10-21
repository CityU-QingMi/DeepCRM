    protected void addResultTypes(PackageConfig.Builder packageContext, Element element) {
        NodeList resultTypeList = element.getElementsByTagName("result-type");

        for (int i = 0; i < resultTypeList.getLength(); i++) {
            Element resultTypeElement = (Element) resultTypeList.item(i);
            String name = resultTypeElement.getAttribute("name");
            String className = resultTypeElement.getAttribute("class");
            String def = resultTypeElement.getAttribute("default");

            Location loc = DomHelper.getLocationObject(resultTypeElement);

            Class clazz = verifyResultType(className, loc);
            if (clazz != null) {
                String paramName = null;
                try {
                    paramName = (String) clazz.getField("DEFAULT_PARAM").get(null);
                } catch (Throwable t) {
                    LOG.debug("The result type [{}] doesn't have a default param [DEFAULT_PARAM] defined!", className, t);
                }
                ResultTypeConfig.Builder resultType = new ResultTypeConfig.Builder(name, className).defaultResultParam(paramName)
                        .location(DomHelper.getLocationObject(resultTypeElement));

                Map<String, String> params = XmlHelper.getParams(resultTypeElement);

                if (!params.isEmpty()) {
                    resultType.addParams(params);
                }
                packageContext.addResultTypeConfig(resultType.build());

                // set the default result type
                if (BooleanUtils.toBoolean(def)) {
                    packageContext.defaultResultType(name);
                }
            }
        }
    }
