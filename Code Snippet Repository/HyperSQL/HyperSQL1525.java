    public void copy(RowSetNavigatorData other, int[] rightColumnIndexes) {

        while (other.next()) {
            Object[] currentData = other.getCurrent();

            addAdjusted(currentData, rightColumnIndexes);
        }

        other.release();
    }
