    private List<SmtpMessage> handleTransaction(final PrintWriter out, final BufferedReader input) throws IOException {
        // Initialize the state machine
        SmtpState smtpState = SmtpState.CONNECT;
        final SmtpRequest smtpRequest = new SmtpRequest(SmtpActionType.CONNECT, Strings.EMPTY, smtpState);

        // Execute the connection request
        final SmtpResponse smtpResponse = smtpRequest.execute();

        // Send initial response
        sendResponse(out, smtpResponse);
        smtpState = smtpResponse.getNextState();

        final List<SmtpMessage> msgList = new ArrayList<>();
        SmtpMessage msg = new SmtpMessage();

        while (smtpState != SmtpState.CONNECT) {
            final String line = input.readLine();

            if (line == null) {
                break;
            }

            // Create request from client input and current state
            final SmtpRequest request = SmtpRequest.createRequest(line, smtpState);
            // Execute request and create response object
            final SmtpResponse response = request.execute();
            // Move to next internal state
            smtpState = response.getNextState();
            // Send response to client
            sendResponse(out, response);

            // Store input in message
            final String params = request.getParams();
            msg.store(response, params);

            // If message reception is complete save it
            if (smtpState == SmtpState.QUIT) {
                msgList.add(msg);
                msg = new SmtpMessage();
            }
        }

        return msgList;
    }
