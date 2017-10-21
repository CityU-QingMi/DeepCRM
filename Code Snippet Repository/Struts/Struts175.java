    protected List<InterceptorMapping> buildInterceptorList(Element element, PackageConfig.Builder context) throws ConfigurationException {
        List<InterceptorMapping> interceptorList = new ArrayList<>();
        NodeList interceptorRefList = element.getElementsByTagName("interceptor-ref");

        for (int i = 0; i < interceptorRefList.getLength(); i++) {
            Element interceptorRefElement = (Element) interceptorRefList.item(i);

            if (interceptorRefElement.getParentNode().equals(element) || interceptorRefElement.getParentNode().getNodeName().equals(element.getNodeName())) {
                List<InterceptorMapping> interceptors = lookupInterceptorReference(context, interceptorRefElement);
                interceptorList.addAll(interceptors);
            }
        }

        return interceptorList;
    }
