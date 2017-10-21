    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object obj = this.getFieldValue(fieldName, object);
        if (obj == null) {
            return;
        }

        Double maxInclusiveToUse = getMaxInclusive();
        Double minInclusiveToUse = getMinInclusive();
        Double maxExclusiveToUse = getMaxExclusive();
        Double minExclusiveToUse = getMinExclusive();

        if (obj.getClass().isArray()) {
            Object[] values = (Object[]) obj;
            validateCollection(maxInclusiveToUse, minInclusiveToUse, maxExclusiveToUse, minExclusiveToUse, Arrays.asList(values));
        } else if (Collection.class.isAssignableFrom(obj.getClass())) {
            Collection values = (Collection) obj;
            validateCollection(maxInclusiveToUse, minInclusiveToUse, maxExclusiveToUse, minExclusiveToUse, values);
        } else {
            validateValue(obj, maxInclusiveToUse, minInclusiveToUse, maxExclusiveToUse, minExclusiveToUse);
        }
    }
