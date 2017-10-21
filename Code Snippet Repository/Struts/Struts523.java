    public void validate(Object object) throws ValidationException {
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
            LOG.warn("Got result of [{}] when trying to get Boolean.", obj);
        }

        if (!answer) {
            LOG.debug("Validation failed on expression [{}] with validated object [{}]", expression, object);
            addActionError(object);
        }
    }
