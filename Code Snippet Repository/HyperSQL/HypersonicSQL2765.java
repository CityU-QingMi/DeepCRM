    public void removeAll() {

        if (!isCached) {
            destroy();
        }

        elementCount.set(0);
        ArrayUtil.fillArray(accessorList, null);

        for (int i = 0; i < nullsList.length; i++) {
            nullsList[i] = false;
        }
    }
