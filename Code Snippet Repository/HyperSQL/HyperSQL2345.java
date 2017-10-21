    Map readStringPairs() throws IOException {

        String key;
        Map    map = new HashMap();

        while (true) {
            key = readString();

            if (key.length() < 1) {
                break;
            }

            map.put(key, readString());
        }

        return map;
    }
