    public boolean next() {

        boolean result = super.next();

        if (!result) {
            return false;
        }

        iterator.next();

        currentRow = iterator.getCurrentRow();

        return result;
    }
