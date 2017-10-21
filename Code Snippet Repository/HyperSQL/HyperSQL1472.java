    public boolean next() {

        if (hasNext()) {
            currentPos++;

            hadNext = true;

            return true;
        } else if (size != 0) {
            currentPos = size;
        }

        hadNext = false;

        return false;
    }
