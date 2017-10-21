    public String pingNow() {
        
        if(getPingTarget() != null) {
            try {
                if (PingConfig.getSuspendPingProcessing()) {
                    log.debug("Ping processing is disabled.");
                    addError("ping.pingProcessingIsSuspended");
                } else {
                    WeblogUpdatePinger.PingResult pingResult = WeblogUpdatePinger.sendPing(getPingTarget(), getActionWeblog());
                    if (pingResult.isError()) {
                        log.debug("Ping Result: " + pingResult);
                        if (pingResult.getMessage() != null && pingResult.getMessage().trim().length() > 0) {
                            addError("ping.transmittedButError");
                            addError(pingResult.getMessage());
                        } else {
                            addError("ping.transmissionFailed");
                        }
                    } else {
                        addMessage("ping.successful");
                    }
                }
            } catch (IOException ex) {
                log.debug(ex);
                addError("ping.transmissionFailed");
                addSpecificMessages(ex);
            } catch (XmlRpcException ex) {
                log.debug(ex);
                addError("ping.transmissionFailed");
                addSpecificMessages(ex);
            }
        }
        
        return execute();
    }
