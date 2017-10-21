    private void logger(long startTime, long middleTime) {
         if (logger && LOG.isInfoEnabled()) {
             long endTime = System.currentTimeMillis();
             long executionTime = middleTime - startTime;
             long processResult = endTime - middleTime;
             long total = endTime - startTime;

             String message = "Executed action [/";
             String namespace = getProxy().getNamespace();
             if ((namespace != null) && (namespace.trim().length() > 1)) {
                 message += namespace + "/";
             }
             message += getProxy().getActionName() + "!" + getProxy().getMethod();
             String extension = handlerSelector.findExtension(
                     ServletActionContext.getRequest().getRequestURI());
             if (extension != null) {
                 message += "!" + extension;
             }
             if (httpHeaders != null) {
                 message += "!" + httpHeaders.getStatus();
             }
             message += "] took " + total + " ms (execution: " + executionTime
                + " ms, result: " + processResult + " ms)";

             LOG.info(message);
         }
    }
