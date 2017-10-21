    public boolean afterLast() {

        if (size == 0) {
            return false;
        }

        reset();

        currentPos = size;

        return true;
    }
