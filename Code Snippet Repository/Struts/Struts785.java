    private static Object nullConvert(Object o) {
        if (o == null) {
            return NULL;
        }

        if (o == NULL || NULL.equals(o)) {
            return null;
        }

        return o;
    }
