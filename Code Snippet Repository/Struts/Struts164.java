    protected void loadGlobalAllowedMethods(PackageConfig.Builder packageContext, Element packageElement) {
        NodeList globalAllowedMethodsElms = packageElement.getElementsByTagName("global-allowed-methods");

        if (globalAllowedMethodsElms.getLength() > 0) {
            Set<String> globalAllowedMethods = new HashSet<>();
            Node n = globalAllowedMethodsElms.item(0).getFirstChild();
            if (n != null) {
                String s = n.getNodeValue().trim();
                if (s.length() > 0) {
                    globalAllowedMethods = TextParseUtil.commaDelimitedStringToSet(s);
                }
            }
            packageContext.addGlobalAllowedMethods(globalAllowedMethods);
        }
    }
