    public static boolean contains(String[] strings, String value, boolean ignoreCase) {
        if (strings != null) {
            for (String string :  strings) {
                if (string.equals(value) || (ignoreCase && string.equalsIgnoreCase(value)))
                    return true;
            }
        }

        return false;
    }
