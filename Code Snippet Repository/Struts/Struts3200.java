    protected void processDynamicAttributes(Attributes a) {
        Map<String, String> dynamicAttributes = (Map<String, String>) context.getParameters().get("dynamicAttributes");
        for (Map.Entry<String, String> entry : dynamicAttributes.entrySet()) {
            if (altSyntax && ComponentUtils.isExpression(entry.getValue())) {
                String value = ObjectUtils.defaultIfNull(findString(entry.getValue()), entry.getValue());
                a.put(entry.getKey(), value);
            } else {
                a.put(entry.getKey(), entry.getValue());
            }
        }
    }
