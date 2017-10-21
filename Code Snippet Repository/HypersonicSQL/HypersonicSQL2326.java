    public boolean relative(int rows) {

        int position = currentPos + rows;

        if (position < 0) {
            beforeFirst();

            return false;
        }

        return absolute(position);
    }
