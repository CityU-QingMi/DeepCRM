    public Type[] getColumnTypes() {

        if (resultColumnTypes.length == indexLimitVisible) {
            return resultColumnTypes;
        }

        Type[] types = new Type[indexLimitVisible];

        ArrayUtil.copyArray(resultColumnTypes, types, types.length);

        return types;
    }
