    public static boolean contains(Object obj1, Object obj2) {
        if ((obj1 == null) || (obj2 == null)) {
            return false;
        }

        if (obj1 instanceof Map) {
            if (((Map) obj1).containsKey(obj2)) {
                return true;
            }
        } if (obj1 instanceof Iterable) {
            for (Object value : ((Iterable) obj1)) {
                if (obj2.equals(value) || (value != null && obj2.toString().equals(value.toString()))) {
                    return true;
                }
            }
        } else if (obj1.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(obj1); i++) {
                Object value = Array.get(obj1, i);

                if (obj2.equals(value) || (value != null && obj2.toString().equals(value.toString()))) {
                    return true;
                }
            }
        } else if (obj1.toString().equals(obj2.toString())) {
            return true;
        } else if (obj1.equals(obj2)) {
            return true;
        }

        return false;
    }
