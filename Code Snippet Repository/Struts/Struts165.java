    protected InterceptorStackConfig loadInterceptorStack(Element element, PackageConfig.Builder context) throws ConfigurationException {
        String name = element.getAttribute("name");

        InterceptorStackConfig.Builder config = new InterceptorStackConfig.Builder(name)
                .location(DomHelper.getLocationObject(element));
        NodeList interceptorRefList = element.getElementsByTagName("interceptor-ref");

        for (int j = 0; j < interceptorRefList.getLength(); j++) {
            Element interceptorRefElement = (Element) interceptorRefList.item(j);
            List<InterceptorMapping> interceptors = lookupInterceptorReference(context, interceptorRefElement);
            config.addInterceptors(interceptors);
        }

        return config.build();
    }
