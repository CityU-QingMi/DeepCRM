    private Row[] getViewToModel() {

        if (viewToModel == null) {
            int tableModelRowCount = tableModel.getRowCount();

            viewToModel = new Row[tableModelRowCount];

            for (int row = 0; row < tableModelRowCount; row++) {
                viewToModel[row] = new Row(row);
            }

            if (isSorting()) {
                Arrays.sort(viewToModel);
            }
        }

        return viewToModel;
    }
