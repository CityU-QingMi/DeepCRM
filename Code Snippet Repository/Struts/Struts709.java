    protected void handleDynamicMethod(ActionMapping mapping, StringBuilder uri) {
        // See WW-3965
        if (StringUtils.isNotEmpty(mapping.getMethod())) {
            if (allowDynamicMethodCalls) {
                // handle "name!method" convention.
                String name = mapping.getName();
                if (!name.contains("!")) {
                    // Append the method as no bang found
                    uri.append("!").append(mapping.getMethod());
                }
            } else {
                uri.append("!").append(mapping.getMethod());
            }
        }
    }
