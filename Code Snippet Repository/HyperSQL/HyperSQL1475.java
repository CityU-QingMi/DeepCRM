    public boolean absolute(int position) {

        if (position < 0) {
            position += size;
        }

        if (position < 0) {
            beforeFirst();

            return false;
        }

        if (position >= size) {
            afterLast();

            return false;
        }

        if (size == 0) {
            return false;
        }

        if (position < currentPos) {
            beforeFirst();
        }

        // go to the tagget row;
        while (position > currentPos) {
            next();
        }

        return true;
    }
