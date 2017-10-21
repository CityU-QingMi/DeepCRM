    public BitMap duplicate() {

        BitMap newMap = new BitMap((int[]) ArrayUtil.duplicateArray(this.map));

        newMap.canChangeSize = this.canChangeSize;
        newMap.initialSize   = this.initialSize;
        newMap.limitPos      = this.limitPos;

        return newMap;
    }
