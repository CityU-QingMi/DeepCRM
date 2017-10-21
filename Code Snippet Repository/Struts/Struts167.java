    protected void loadInterceptors(PackageConfig.Builder context, Element element) throws ConfigurationException {
        NodeList interceptorList = element.getElementsByTagName("interceptor");

        for (int i = 0; i < interceptorList.getLength(); i++) {
            Element interceptorElement = (Element) interceptorList.item(i);
            String name = interceptorElement.getAttribute("name");
            String className = interceptorElement.getAttribute("class");

            Map<String, String> params = XmlHelper.getParams(interceptorElement);
            InterceptorConfig config = new InterceptorConfig.Builder(name, className)
                    .addParams(params)
                    .location(DomHelper.getLocationObject(interceptorElement))
                    .build();

            context.addInterceptorConfig(config);
        }

        loadInterceptorStacks(element, context);
    }
