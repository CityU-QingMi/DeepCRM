    private void processError(int code) {

        String msg;

        server.printWithThread("processError " + code);

        switch (code) {

            case HttpURLConnection.HTTP_BAD_REQUEST :
                msg = getHead(HEADER_BAD_REQUEST, false, null, 0);
                msg += ResourceBundleHandler.getString(
                    WebServer.webBundleHandle, "BAD_REQUEST");
                break;

            case HttpURLConnection.HTTP_FORBIDDEN :
                msg = getHead(HEADER_FORBIDDEN, false, null, 0);
                msg += ResourceBundleHandler.getString(
                    WebServer.webBundleHandle, "FORBIDDEN");
                break;

            case HttpURLConnection.HTTP_NOT_FOUND :
            default :
                msg = getHead(HEADER_NOT_FOUND, false, null, 0);
                msg += ResourceBundleHandler.getString(
                    WebServer.webBundleHandle, "NOT_FOUND");
                break;
        }

        try {
            OutputStream os =
                new BufferedOutputStream(socket.getOutputStream());

            os.write(msg.getBytes(ENCODING));
            os.flush();
            os.close();
        } catch (Exception e) {
            server.printError("processError: " + e.toString());
            server.printStackTrace(e);
        }
    }
