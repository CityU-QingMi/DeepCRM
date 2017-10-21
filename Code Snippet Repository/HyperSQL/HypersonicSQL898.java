    public void closeAllTransactionNavigators() {

        if (resultMap == null) {
            return;
        }

        Iterator it = resultMap.values().iterator();

        while (it.hasNext()) {
            Result result = (Result) it.next();

            if (!ResultProperties.isHoldable(result.rsProperties)) {
                result.getNavigator().release();
                it.remove();
            }
        }
    }
