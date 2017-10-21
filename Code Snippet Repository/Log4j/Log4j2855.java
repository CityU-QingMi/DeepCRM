    @Setup
    public void setup() {
        openHashMapContextData = new OpenHashStringMap<>();
        sortedStringArrayMap = new SortedArrayStringMap();
        map = new HashMap<>();

        keys = new String[count];
        final Random r = new Random();
        for (int j = 0; j < keys.length; j++) {
            final char[] str = new char[length];
            for (int i = 0; i < str.length; i++) {
                str[i] = (char) r.nextInt();
            }
            keys[j] = new String(str);
        }

        populatedMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            populatedMap.put(keys[i], value);
        }
        populatedSortedStringArrayMap = new SortedArrayStringMap();
        for (int i = 0; i < count; i++) {
            populatedSortedStringArrayMap.putValue(keys[i], value);
        }
        populatedOpenHashContextData = new OpenHashStringMap<>();
        for (int i = 0; i < count; i++) {
            populatedOpenHashContextData.putValue(keys[i], value);
        }
    }
