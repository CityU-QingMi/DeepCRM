    public String getStateDescriptor() {

        String state;

        switch (serverState) {

            case ServerConstants.SERVER_STATE_SHUTDOWN :
                state = "SHUTDOWN";
                break;

            case ServerConstants.SERVER_STATE_OPENING :
                state = "OPENING";
                break;

            case ServerConstants.SERVER_STATE_CLOSING :
                state = "CLOSING";
                break;

            case ServerConstants.SERVER_STATE_ONLINE :
                state = "ONLINE";
                break;

            default :
                state = "UNKNOWN";
                break;
        }

        return state;
    }
