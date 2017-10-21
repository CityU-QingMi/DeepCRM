    public BitMap(int size, boolean extend) {

        int words = size / Integer.SIZE;

        if (size == 0 || size % Integer.SIZE != 0) {
            words++;
        }

        map           = new int[words];
        canChangeSize = extend;
        limitPos      = size;
        initialSize   = size;
    }
