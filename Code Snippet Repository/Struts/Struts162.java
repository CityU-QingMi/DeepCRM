    protected List<ExceptionMappingConfig> buildExceptionMappings(Element element, PackageConfig.Builder packageContext) {
        NodeList exceptionMappingEls = element.getElementsByTagName("exception-mapping");

        List<ExceptionMappingConfig> exceptionMappings = new ArrayList<>();

        for (int i = 0; i < exceptionMappingEls.getLength(); i++) {
            Element ehElement = (Element) exceptionMappingEls.item(i);

            if (ehElement.getParentNode().equals(element) || ehElement.getParentNode().getNodeName().equals(element.getNodeName())) {
                String emName = ehElement.getAttribute("name");
                String exceptionClassName = ehElement.getAttribute("exception");
                String exceptionResult = ehElement.getAttribute("result");

                Map<String, String> params = XmlHelper.getParams(ehElement);

                if (StringUtils.isEmpty(emName)) {
                    emName = exceptionResult;
                }

                ExceptionMappingConfig ehConfig = new ExceptionMappingConfig.Builder(emName, exceptionClassName, exceptionResult)
                        .addParams(params)
                        .location(DomHelper.getLocationObject(ehElement))
                        .build();
                exceptionMappings.add(ehConfig);
            }
        }

        return exceptionMappings;
    }
