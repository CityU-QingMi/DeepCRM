    public static String getString(String val) {

        if (val == null || val.length() > maxStringLength) {
            return val;
        }

        synchronized (stringPool) {
            return stringPool.getOrAddString(val);
        }
    }
