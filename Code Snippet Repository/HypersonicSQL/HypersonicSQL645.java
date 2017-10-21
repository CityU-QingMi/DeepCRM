    public int getParameterCount(int type) {

        int count = 0;

        for (int i = 0; i < parameterList.size(); i++) {
            ColumnSchema col = (ColumnSchema) parameterList.get(i);

            if (col.getParameterMode() == type) {
                count++;
            }
        }

        return count;
    }
