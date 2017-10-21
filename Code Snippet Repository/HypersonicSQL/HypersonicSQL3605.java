    public Boolean match(Session session, String string, String[] array) {

        if (string == null || array == null) {
            return null;
        }

        String  s      = null;
        int     offset = 0;
        boolean match  = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {

                // single char skip
                offset++;

                match = true;
            } else if (array[i].length() == 0) {

                // string skip
                match = false;
            }

            if (match) {
                if (offset + array[i].length() > string.length()) {
                    return Boolean.FALSE;
                }

                s = string.substring(offset, offset + array[i].length());

                if (collation.compare(s, array[i]) != 0) {
                    return Boolean.FALSE;
                }

                offset += array[i].length();
            } else {
                int index = string.indexOf(array[i], offset);

                if (index < 0) {
                    return Boolean.FALSE;
                }

                offset = index + array[i].length();
                match  = true;
            }
        }

        return Boolean.TRUE;
    }
