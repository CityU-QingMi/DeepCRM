    public boolean next() {

        if (currentPos < size - 1) {
            currentPos++;

            return true;
        }

        currentPos = size - 1;

        return false;
    }
