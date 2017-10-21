        private Object tryConvert(Field field, int index, ITypeConverter<?> converter, String value, Class<?> type)
                throws Exception {
            try {
                return converter.convert(value);
            } catch (ParameterException ex) {
                throw new ParameterException(ex.getMessage() + optionDescription(" for ", field, index));
            } catch (Exception other) {
                String desc = optionDescription(" for ", field, index) + ": " + other;
                throw new ParameterException("Could not convert '" + value + "' to " + type.getSimpleName() + desc, other);
            }
        }
