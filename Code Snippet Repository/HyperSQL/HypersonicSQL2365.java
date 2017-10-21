    public void removeCurrent() {

        if (currentRow != null) {
            iterator.removeCurrent();

            currentRow = null;

            currentPos--;
            size--;
        }
    }
