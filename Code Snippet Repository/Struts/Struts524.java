    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();

        Boolean answer = Boolean.FALSE;
        Object obj = null;

        try {
            obj = getFieldValue(expression, object);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            // let this pass, but it will be logged right below
        }

        if ((obj != null) && (obj instanceof Boolean)) {
            answer = (Boolean) obj;
        } else {
            LOG.warn("Got result of {} when trying to get Boolean.", obj);
        }

        if (!answer) {
            addFieldError(fieldName, object);
        }
    }
