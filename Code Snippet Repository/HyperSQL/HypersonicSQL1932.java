    public void removeAll() {

        hasChanged = true;

        ArrayUtil.clearArray(ArrayUtil.CLASS_CODE_INT, keys, 0, count);
        ArrayUtil.clearArray(ArrayUtil.CLASS_CODE_INT, values, 0, count);

        count  = 0;
        sorted = true;
    }
