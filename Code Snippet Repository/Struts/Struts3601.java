    @Override
    public String invoke() throws Exception {
        long startTime = 0;

        boolean executeResult = false;
        if (isFirstInterceptor) {
            startTime = System.currentTimeMillis();
            executeResult = true;
            isFirstInterceptor = false;
        }

        // Normal invoke without execute the result
        proxy.setExecuteResult(false);
        resultCode = super.invoke();

        // Execute the result when the last interceptor has finished
        if (executeResult) {
            long middleTime = System.currentTimeMillis();

            try {
                processResult();

            } catch (ConfigurationException e) {
                throw e;

            } catch (Exception e) {

                // Error processing the result
                LOG.error("Exception processing the result.", e);

                if (!ServletActionContext.getResponse().isCommitted()) {
                    ServletActionContext.getResponse()
                        .setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    stack.set("exception", e);
                    result = null;
                    resultCode = null;
                    processResult();
                }
            }

            // Log execution + result time
            logger(startTime, middleTime);
        }

        return resultCode;
    }
