    protected ValidationError buildValidationError(ConstraintViolation violation, String message) {
        OValContext context = violation.getContext();
        if (context instanceof FieldContext) {
            Field field = ((FieldContext) context).getField();
            String className = field.getDeclaringClass().getName();

            //the default OVal message shows the field name as ActionClass.fieldName
            String finalMessage = StringUtils.removeStart(message, className + ".");

            return new ValidationError(field.getName(), finalMessage);
        } else if (context instanceof MethodReturnValueContext) {
            Method method = ((MethodReturnValueContext) context).getMethod();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();

            //the default OVal message shows the field name as ActionClass.fieldName
            String finalMessage = StringUtils.removeStart(message, className + ".");

            String fieldName = null;
            if (methodName.startsWith("get")) {
                fieldName = StringUtils.uncapitalize(StringUtils.removeStart(methodName, "get"));
            } else if (methodName.startsWith("is")) {
                fieldName = StringUtils.uncapitalize(StringUtils.removeStart(methodName, "is"));
            }

            //the result will have the full method name, like "getName()", replace it by "name" (obnly if it is a field)
            if (fieldName != null)
                finalMessage = finalMessage.replaceAll(methodName + "\\(.*?\\)", fieldName);

            return new ValidationError(StringUtils.defaultString(fieldName, methodName), finalMessage);
        }

        return new ValidationError(violation.getCheckName(), message);
    }
