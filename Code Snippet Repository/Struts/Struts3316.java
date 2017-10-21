    private void processAnnotatedMethod(org.apache.struts2.json.smd.SMD smd, Method method) {
        SMDMethod smdMethodAnnotation = method.getAnnotation(SMDMethod.class);
        // SMDMethod annotation is required
        if (shouldProcessMethod(method, smdMethodAnnotation)) {
            String methodName = readMethodName(method, smdMethodAnnotation);
            org.apache.struts2.json.smd.SMDMethod smdMethod = new org.apache.struts2.json.smd.SMDMethod(methodName);
            smd.addSMDMethod(smdMethod);

            // find params for this method
            processMethodsParameters(method, smdMethod);

        } else if(LOG.isDebugEnabled()) {
            LOG.debug("Ignoring property " + method.getName());
        }
    }
