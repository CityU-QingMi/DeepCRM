    public static AllowedMethods build(boolean strictMethodInvocation, Set<String> methods, String defaultRegex) {

        Set<AllowedMethod> allowedMethods = new HashSet<>();
        for (String method : methods) {
            boolean isPattern = false;
            StringBuilder methodPattern = new StringBuilder();
            int len = method.length();
            char c;
            for (int x = 0; x < len; x++) {
                c = method.charAt(x);
                if (x < len - 2 && c == '{' && '}' == method.charAt(x + 2)) {
                    methodPattern.append(defaultRegex);
                    isPattern = true;
                    x += 2;
                } else {
                    methodPattern.append(c);
                }
            }

            if (isPattern && !method.startsWith("regex:") && !strictMethodInvocation) {
                allowedMethods.add(new PatternAllowedMethod(methodPattern.toString(), method));
            } else if (method.startsWith("regex:")) {
                String pattern = method.substring(method.indexOf(":") + 1);
                allowedMethods.add(new PatternAllowedMethod(pattern, method));
            } else if (method.contains("*") && !method.startsWith("regex:") && !strictMethodInvocation) {
                String pattern = method.replace("*", defaultRegex);
                allowedMethods.add(new PatternAllowedMethod(pattern, method));
            } else if (!isPattern) {
                allowedMethods.add(new LiteralAllowedMethod(method));
            } else {
                LOG.trace("Ignoring method name: [{}] when SMI is set to [{}]", method, strictMethodInvocation);
            }
        }

        LOG.debug("Defined allowed methods: {}", allowedMethods);

        return new AllowedMethods(strictMethodInvocation, allowedMethods, defaultRegex);
    }
