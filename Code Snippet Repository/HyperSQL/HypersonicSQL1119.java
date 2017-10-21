    public void dropIndex(Session session, int todrop) {

        Index[] list = (Index[]) ArrayUtil.toAdjustedArray(indexList, null,
            todrop, -1);

        for (int i = 0; i < list.length; i++) {
            list[i].setPosition(i);
        }

        resetAccessorKeys(session, list);

        indexList = list;

        setBestRowIdentifiers();
    }
