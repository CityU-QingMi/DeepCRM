    public void execute(ActionInvocation invocation) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        ValueStack stack = ActionContext.getContext().getValueStack();

        if (status != -1) {
            response.setStatus(status);
        }

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String value = entry.getValue();
                String finalValue = parse ? TextParseUtil.translateVariables(value, stack) : value;
                response.addHeader(entry.getKey(), finalValue);
            }
        }

        if (status == -1 && error != null) {
            int errorCode = -1;
            try {
                errorCode = Integer.parseInt(parse ? TextParseUtil.translateVariables(error, stack) : error);
            } catch (Exception e) {
                LOG.error("Cannot parse errorCode [{}] value as Integer!", error, e);
            }
            if (errorCode != -1) {
                if (errorMessage != null) {
                    String finalMessage = parse ? TextParseUtil.translateVariables(errorMessage, stack) : errorMessage;
                    response.sendError(errorCode, finalMessage);
                } else {
                    response.sendError(errorCode);
                }
            }
        }
    }
