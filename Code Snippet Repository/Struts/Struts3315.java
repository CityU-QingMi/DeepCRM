    public org.apache.struts2.json.smd.SMD generate(ActionInvocation actionInvocation) {
        ActionContext actionContext = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);

        Class clazz = rootObject.getClass();
        org.apache.struts2.json.smd.SMD smd = new org.apache.struts2.json.smd.SMD();
        // URL
        smd.setServiceUrl(request.getRequestURI());

        // customize SMD
        org.apache.struts2.json.annotations.SMD smdAnnotation = (SMD) clazz.getAnnotation(SMD.class);
        if (smdAnnotation != null) {
            smd.setObjectName(smdAnnotation.objectName());
            smd.setServiceType(smdAnnotation.serviceType());
            smd.setVersion(smdAnnotation.version());
        }

        // get public methods
        Method[] methods = JSONUtil.listSMDMethods(clazz, ignoreInterfaces);

        for (Method method : methods) {
            processAnnotatedMethod(smd, method);
        }
        return smd;

    }
