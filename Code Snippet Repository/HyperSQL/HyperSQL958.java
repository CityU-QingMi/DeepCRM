    public static void projectMap(int[] mainMap, int[] subMap,
                                  int[] newSubMap) {

        for (int i = 0; i < subMap.length; i++) {
            for (int j = 0; j < mainMap.length; j++) {
                if (subMap[i] == mainMap[j]) {
                    newSubMap[i] = j;

                    break;
                }
            }
        }
    }
