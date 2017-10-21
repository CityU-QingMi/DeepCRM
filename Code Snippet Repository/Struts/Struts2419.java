    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = PropertyUtils.getProperty(value, this.firstFieldName);
            final Object secondObj = PropertyUtils.getProperty(value, this.secondFieldName);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ex) {
            LOG.info("Error while getting values from object", ex);
            return false;
        }

    }
