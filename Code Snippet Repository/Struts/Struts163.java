    protected Set<String> buildAllowedMethods(Element element, PackageConfig.Builder packageContext) {
        NodeList allowedMethodsEls = element.getElementsByTagName("allowed-methods");

        Set<String> allowedMethods;
        if (allowedMethodsEls.getLength() > 0) {
            // user defined 'allowed-methods' so used them whatever Strict DMI was enabled or not
            allowedMethods = new HashSet<>(packageContext.getGlobalAllowedMethods());

            if (allowedMethodsEls.getLength() > 0) {
                Node n = allowedMethodsEls.item(0).getFirstChild();
                if (n != null) {
                    String s = n.getNodeValue().trim();
                    if (s.length() > 0) {
                        allowedMethods.addAll(TextParseUtil.commaDelimitedStringToSet(s));
                    }
                }
            }
        } else if (packageContext.isStrictMethodInvocation()) {
            // user enabled Strict DMI but didn't defined action specific 'allowed-methods' so we use 'global-allowed-methods' only
            allowedMethods = new HashSet<>(packageContext.getGlobalAllowedMethods());
        } else {
            // Strict DMI is disabled so any method can be called
            allowedMethods = new HashSet<>();
            allowedMethods.add(ActionConfig.WILDCARD);
        }

        LOG.debug("Collected allowed methods: {}", allowedMethods);

        return Collections.unmodifiableSet(allowedMethods);
    }
