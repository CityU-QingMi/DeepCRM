    private int[] getModelToView() {

        if (modelToView == null) {
            int n = getViewToModel().length;

            modelToView = new int[n];

            for (int i = 0; i < n; i++) {
                modelToView[modelIndex(i)] = i;
            }
        }

        return modelToView;
    }
