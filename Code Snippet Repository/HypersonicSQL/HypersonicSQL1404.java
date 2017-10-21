    private static int getTranslatePointer(int pointer, LongLookup lookup) {

        int newPointer = 0;

        if (pointer != NodeAVL.NO_POS) {
            if (lookup == null) {
                newPointer = pointer;
            } else {
                newPointer = (int) lookup.lookup(pointer);
            }
        }

        return newPointer;
    }
