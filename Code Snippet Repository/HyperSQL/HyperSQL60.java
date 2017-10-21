    public int getColumnIndex(String name) {

        int i = findColumn(name);

        if (i == -1) {
            throw Error.error(ErrorCode.X_42501, name);
        }

        return i;
    }
