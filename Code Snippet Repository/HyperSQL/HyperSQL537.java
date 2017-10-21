    void fetchResult() throws SQLException {

        super.fetchResult();

        if (resultIn.getType() == ResultConstants.CALL_RESPONSE) {
            Object[] data = resultIn.getParameterData();

            for (int i = 0; i < parameterValues.length; i++) {
                parameterValues[i] = data[i];
            }
        }
    }
