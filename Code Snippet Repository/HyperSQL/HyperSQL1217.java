    public HashMappedList getAsMap() {

        HashMappedList map = new HashMappedList();

        while (true) {
            HsqlArrayList list = getSection();

            if (list.size() < 1) {
                break;
            }

            String key   = (String) list.get(0);
            String value = LineGroupReader.convertToString(list, 1);

            map.put(key, value);
        }

        return map;
    }
