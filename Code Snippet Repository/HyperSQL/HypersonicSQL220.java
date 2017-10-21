    private static String translateWithMap(String source,
                                           IntKeyIntValueHashMap map) {

        StringBuffer sb = new StringBuffer(source.length());

        for (int i = 0; i < source.length(); i++) {
            int character = source.charAt(i);
            int value     = map.get(character, -2);

            if (value == -2) {
                sb.append((char) character);
            } else if (value == -1) {

                //
            } else {
                sb.append((char) value);
            }
        }

        return sb.toString();
    }
