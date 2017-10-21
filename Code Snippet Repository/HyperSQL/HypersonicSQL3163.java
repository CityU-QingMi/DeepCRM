    public static int getDefaultPort(int protocol, boolean isTls) {

        switch (protocol) {

            case SC_PROTOCOL_HSQL : {
                return isTls ? SC_DEFAULT_HSQLS_SERVER_PORT
                             : SC_DEFAULT_HSQL_SERVER_PORT;
            }
            case SC_PROTOCOL_HTTP : {
                return isTls ? SC_DEFAULT_HTTPS_SERVER_PORT
                             : SC_DEFAULT_HTTP_SERVER_PORT;
            }
            case SC_PROTOCOL_BER : {
                return isTls ? -1
                             : SC_DEFAULT_BER_SERVER_PORT;
            }
            default : {
                return -1;
            }
        }
    }
