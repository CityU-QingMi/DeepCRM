    public static void reorderMaps(int[] mainMap, int[] firstMap,
                                   int[] secondMap) {

        for (int i = 0; i < mainMap.length; i++) {
            for (int j = i; j < firstMap.length; j++) {
                if (mainMap[i] == firstMap[j]) {
                    int temp = firstMap[i];

                    firstMap[i]  = firstMap[j];
                    firstMap[j]  = temp;
                    temp         = secondMap[i];
                    secondMap[i] = secondMap[j];
                    secondMap[j] = temp;

                    break;
                }
            }
        }
    }
