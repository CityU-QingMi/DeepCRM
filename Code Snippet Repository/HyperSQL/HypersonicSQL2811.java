    public static void setLocale(Locale l) throws IllegalArgumentException {

        synchronized (mutex) {
            if (l == null) {
                throw new IllegalArgumentException("null locale");
            }

            locale = l;
        }
    }
