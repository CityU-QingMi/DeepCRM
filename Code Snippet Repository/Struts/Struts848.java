    public String toStringSafe(Object obj) {
        try {
            if (obj != null) {
                return String.valueOf(obj);
            }
            return "";
        } catch (Exception e) {
            return "Exception thrown: " + e;
        }
    }
