    protected String determineNamespace(String namespace, ValueStack stack, HttpServletRequest req) {
        String result;

        if (namespace == null) {
            result = TagUtils.buildNamespace(actionMapper, stack, req);
        } else {
            result = findString(namespace);
        }

        if (result == null) {
            result = "";
        }

        return result;
    }
