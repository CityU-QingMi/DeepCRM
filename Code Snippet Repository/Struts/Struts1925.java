    public void testErrorMessageIsParsedAndSet() throws Exception {
        ActionContext.getContext().getValueStack().set("errMsg", "abc");
        ActionContext.getContext().getValueStack().set("errCode", "404");
        result.setError("${errCode}");
        result.setErrorMessage("${errMsg}");
        
        responseMock.expect("sendError", C.args(C.eq(404), C.eq("abc")));
        result.execute(invocation);
        responseMock.verify();
    }
