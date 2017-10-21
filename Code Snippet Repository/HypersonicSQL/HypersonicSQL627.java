    public RangeVariable getRangeForTableName(String name) {

        if (tableAlias != null) {
            return super.getRangeForTableName(name);
        }

        for (int i = 0; i < rangeArray.length; i++) {
            RangeVariable range = rangeArray[i].getRangeForTableName(name);

            if (range != null) {
                return range;
            }
        }

        return null;
    }
