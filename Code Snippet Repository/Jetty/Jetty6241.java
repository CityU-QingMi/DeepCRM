    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        PathParam pathparam = param.getAnnotation(PathParam.class);
        if(pathparam != null)
        {
            param.bind(Role.PATH_PARAM);
            param.setPathParamName(pathparam.value());
            return true;
        }

        return false;
    }
