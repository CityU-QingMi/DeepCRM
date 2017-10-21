    private void processMethodsParameters(Method method, org.apache.struts2.json.smd.SMDMethod smdMethod) {
        int parametersCount = method.getParameterTypes().length;
        if (parametersCount > 0) {

            Annotation[][] parameterAnnotations = method.getParameterAnnotations();

            for (int i = 0; i < parametersCount; i++) {
                processParameter(smdMethod, parameterAnnotations[i], i);
            }
        }
    }
