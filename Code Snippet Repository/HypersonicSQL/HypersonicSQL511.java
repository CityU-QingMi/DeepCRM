    String[] getColumnNames() {

        if (unionCorrespondingColumns == null) {
            return leftQueryExpression.getColumnNames();
        }

        String[] names = new String[unionCorrespondingColumns.size()];

        unionCorrespondingColumns.toArray(names);

        return names;
    }
