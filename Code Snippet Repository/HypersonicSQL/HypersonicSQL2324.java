    public boolean last() {

        if (size == 0) {
            return false;
        }

        if (isAfterLast()) {
            beforeFirst();
        }

        while (hasNext()) {
            next();
        }

        return true;
    }
