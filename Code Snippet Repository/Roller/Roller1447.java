    public void runTask() {
        
        try {
            log.debug("task started");
            
            PingQueueProcessor.getInstance().processQueue();
            WebloggerFactory.getWeblogger().flush();
            
            log.debug("task completed");
            
        } catch (WebloggerException e) {
            log.error("Error while processing ping queue", e);
        } catch (Exception ee) {
            log.error("unexpected exception", ee);
        } finally {
            // always release
            WebloggerFactory.getWeblogger().release();
        }
        
    }
