    public String handleResult(ActionInvocation invocation, Object methodResult, Object target) throws IOException {
        String resultCode = readResultCode(methodResult);
        Integer statusCode = readStatusCode(methodResult);
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpServletResponse res = ServletActionContext.getResponse();
        ActionConfig actionConfig = invocation.getProxy().getConfig();

        if(statusCode != null) {
            res.setStatus(statusCode);
        }

        ContentTypeHandler handler = getHandlerForResponse(req, res);
        if (handler != null) {
            String extCode = resultCode + "." + handler.getExtension();
            if (actionConfig.getResults().get(extCode) != null) {
                resultCode = extCode;
            } else {
                StringWriter writer = new StringWriter();
                resultCode = handler.fromObject(invocation, target, resultCode, writer);
                String text = writer.toString();
                if (text.length() > 0) {
                    byte[] data = text.getBytes("UTF-8");
                    res.setContentLength(data.length);
                    res.setContentType(handler.getContentType());
                    res.getOutputStream().write(data);
                    res.getOutputStream().flush();
                }
            }
        }
        return resultCode;
    }
