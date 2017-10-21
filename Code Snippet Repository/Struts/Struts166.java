    protected void loadInterceptorStacks(Element element, PackageConfig.Builder context) throws ConfigurationException {
        NodeList interceptorStackList = element.getElementsByTagName("interceptor-stack");

        for (int i = 0; i < interceptorStackList.getLength(); i++) {
            Element interceptorStackElement = (Element) interceptorStackList.item(i);

            InterceptorStackConfig config = loadInterceptorStack(interceptorStackElement, context);

            context.addInterceptorStackConfig(config);
        }
    }
