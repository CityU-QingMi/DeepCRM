    List<Param> lookupParams() {
        List<Param> params = new ArrayList<Param>();

        List<String> parameterNames = lookupParameterNames();
        List<Class<?>> parameterTypes = lookupParameterTypes();
        Iterator<Class<?>> parameterTypeIterator = parameterTypes.iterator();

        // If there are more parameter names than parameter types it means that we are
        // using instantiable GXPs and there are 1 or more constructor parameters.
        // Constructor params will always be first in the list, so just drop an appropriate
        // number of elements from the beginning of the list.
        if (parameterNames.size() > parameterTypes.size()) {
            parameterNames = parameterNames.subList(parameterNames.size() - parameterTypes.size(), parameterNames.size());
        }

        for (String name : parameterNames) {
            Class paramType = parameterTypeIterator.next();
            Param param = new Param(gxpClass, name, paramType);
            params.add(param);

            if (param.isBody()) {
                hasBodyParam = true;
            }

            if (param.isOptional()) {
                defaultValues.put(param.getName(), param.getDefaultValue());
            }
        }

        this.defaultValues = Collections.unmodifiableMap(this.defaultValues);
        return Collections.unmodifiableList(params);
    }
