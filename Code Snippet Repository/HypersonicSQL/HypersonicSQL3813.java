    public static boolean matches(Type[] one, Type[] other) {

        for (int i = 0; i < one.length; i++) {
            if (one[i].typeCode != other[i].typeCode) {
                return false;
            }
        }

        return true;
    }
