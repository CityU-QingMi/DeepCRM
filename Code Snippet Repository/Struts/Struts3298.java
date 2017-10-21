    @SuppressWarnings("")
    protected String buildResponse(ValidationAware validationAware) {
        //should we use FreeMarker here?
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");

        if (validationAware.hasErrors()) {
            //action errors
            if (validationAware.hasActionErrors()) {
                sb.append("\"errors\":");
                sb.append(buildArray(validationAware.getActionErrors()));
            }

            //field errors
            if (validationAware.hasFieldErrors()) {
                if (validationAware.hasActionErrors())
                    sb.append(",");
                sb.append("\"fieldErrors\": {");
                Map<String, List<String>> fieldErrors = validationAware
                    .getFieldErrors();
                for (Map.Entry<String, List<String>> fieldError : fieldErrors
                    .entrySet()) {
                    sb.append("\"");
                    //if it is model driven, remove "model." see WW-2721
                    String fieldErrorKey = fieldError.getKey();
                    sb.append(((validationAware instanceof ModelDriven) &&  fieldErrorKey.startsWith("model."))? fieldErrorKey.substring(6)
                            : fieldErrorKey);
                    sb.append("\":");
                    sb.append(buildArray(fieldError.getValue()));
                    sb.append(",");
                }
                //remove trailing comma, IE creates an empty object, duh
                sb.deleteCharAt(sb.length() - 1);
                sb.append("}");
            }
        }

        sb.append("}");
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        return sb.toString();
    }
