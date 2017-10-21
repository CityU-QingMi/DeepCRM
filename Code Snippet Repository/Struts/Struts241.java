    private static Object[] getParameters(Member member, InternalContext context, ParameterInjector[] parameterInjectors) {
        if (parameterInjectors == null) {
            return null;
        }

        Object[] parameters = new Object[parameterInjectors.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = parameterInjectors[i].inject(member, context);
        }
        return parameters;
    }
