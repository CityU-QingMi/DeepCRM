    private static boolean containsPropertyRequiringLookup(final Property[] properties) {
        if (properties == null) {
            return false;
        }
        for (int i = 0; i < properties.length; i++) {
            if (properties[i].isValueNeedsLookup()) {
                return true;
            }
        }
        return false;
    }
