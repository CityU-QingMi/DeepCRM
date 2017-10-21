    public void setBatchedPreparedExecuteRequest() {

        mode = ResultConstants.BATCHEXECUTE;

        if (navigator == null) {
            navigator = new RowSetNavigatorClient(4);
        } else {
            navigator.clear();
        }

        updateCount    = 0;
        this.fetchSize = 0;
    }
