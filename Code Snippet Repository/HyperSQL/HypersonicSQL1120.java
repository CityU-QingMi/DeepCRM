    static Index[] getNewIndexArray(Index index, Index[] list) {

        int i = 0;

        for (; i < list.length; i++) {
            Index current = list[i];
            int order = index.getIndexOrderValue()
                        - current.getIndexOrderValue();

            if (order < 0) {
                break;
            }
        }

        list = (Index[]) ArrayUtil.toAdjustedArray(list, index, i, 1);

        for (i = 0; i < list.length; i++) {
            list[i].setPosition(i);
        }

        return list;
    }
