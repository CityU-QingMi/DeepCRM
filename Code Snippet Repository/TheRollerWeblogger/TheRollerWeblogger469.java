    private static String getRequestAttribute(HttpServletRequest request, String attributeName) {

        String attr = null;
        Object attrObj = request.getAttribute(attributeName);
        if (attrObj instanceof String) {
            attr = (String)attrObj;
        } else if (attrObj instanceof Set) {
            Set attrSet = (Set)attrObj;           
            if (!attrSet.isEmpty()) {
                attr = (String)attrSet.iterator().next();
            }
        }

        return attr;
    }
