    public void testErrorMessageIsNotParsedAndSet() throws Exception {
        ActionContext.getContext().getValueStack().set("errMsg", "abc");
        result.setError("404");
        result.setParse(false);
        result.setErrorMessage("${errMsg}");
        
        responseMock.expect("sendError", C.args(C.eq(404), C.eq("${errMsg}")));
        result.execute(invocation);
        responseMock.verify();
    }
