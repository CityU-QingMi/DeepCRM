    public int getColumnCount() {

        if (unionCorrespondingColumns == null) {
            int left  = leftQueryExpression.getColumnCount();
            int right = rightQueryExpression.getColumnCount();

            if (left != right) {
                throw Error.error(ErrorCode.X_42594);
            }

            return left;
        }

        return unionCorrespondingColumns.size();
    }
