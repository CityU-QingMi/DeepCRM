    private static IntKeyIntValueHashMap getTranslationMap(String source,
            String dest) {

        IntKeyIntValueHashMap map = new IntKeyIntValueHashMap();

        for (int i = 0; i < source.length(); i++) {
            int character = source.charAt(i);

            if (i >= dest.length()) {
                map.put(character, -1);

                continue;
            }

            int value = dest.charAt(i);

            map.put(character, value);
        }

        return map;
    }
