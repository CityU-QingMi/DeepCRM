    public static Result newResult(int type) {

        RowSetNavigator navigator = null;
        Result          result    = null;

        switch (type) {

            case ResultConstants.CALL_RESPONSE :
            case ResultConstants.EXECUTE :
            case ResultConstants.UPDATE_RESULT :
                break;

            case ResultConstants.BATCHEXECUTE :
            case ResultConstants.BATCHEXECDIRECT :
                navigator = new RowSetNavigatorClient(4);
                break;

            case ResultConstants.SETSESSIONATTR :
            case ResultConstants.PARAM_METADATA :
                navigator = new RowSetNavigatorClient(1);
                break;

            case ResultConstants.BATCHEXECRESPONSE :
                navigator = new RowSetNavigatorClient(4);
                break;

            case ResultConstants.DATA :
            case ResultConstants.DATAHEAD :
            case ResultConstants.DATAROWS :
            case ResultConstants.GENERATED :
                break;

            case ResultConstants.LARGE_OBJECT_OP :
                throw Error.runtimeError(ErrorCode.U_S0500, "Result");
            default :
        }

        result           = new Result(type);
        result.navigator = navigator;

        return result;
    }
