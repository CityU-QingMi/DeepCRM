    public RowSetNavigator initialiseNavigator() {

        switch (mode) {

            case ResultConstants.BATCHEXECUTE :
            case ResultConstants.BATCHEXECDIRECT :
            case ResultConstants.BATCHEXECRESPONSE :
            case ResultConstants.SETSESSIONATTR :
            case ResultConstants.PARAM_METADATA :
                navigator.beforeFirst();

                return navigator;

            case ResultConstants.DATA :
            case ResultConstants.DATAHEAD :
            case ResultConstants.GENERATED :
                navigator.reset();

                return navigator;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Result");
        }
    }
