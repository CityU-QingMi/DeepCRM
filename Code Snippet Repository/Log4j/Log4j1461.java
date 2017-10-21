        private int applyValueToSingleValuedField(Field field,
                                                  Range arity,
                                                  Stack<String> args,
                                                  Class<?> cls,
                                                  Set<Field> initialized) throws Exception {
            boolean noMoreValues = args.isEmpty();
            String value = args.isEmpty() ? null : trim(args.pop()); // unquote the value
            int result = arity.min; // the number or args we need to consume

            // special logic for booleans: BooleanConverter accepts only "true" or "false".
            if ((cls == Boolean.class || cls == Boolean.TYPE) && arity.min <= 0) {

                // boolean option with arity = 0..1 or 0..*: value MAY be a param
                if (arity.max > 0 && ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value))) {
                    result = 1;            // if it is a varargs we only consume 1 argument if it is a boolean value
                } else {
                    if (value != null) {
                        args.push(value); // we don't consume the value
                    }
                    Boolean currentValue = (Boolean) field.get(command);
                    value = String.valueOf(currentValue == null ? true : !currentValue); // #147 toggle existing boolean value
                }
            }
            if (noMoreValues && value == null) {
                return 0;
            }
            if (initialized != null) {
                if (initialized.contains(field) && !isOverwrittenOptionsAllowed()) {
                    throw new OverwrittenOptionException(optionDescription("", field, 0) +  " should be specified only once");
                }
                initialized.add(field);
            }
            ITypeConverter<?> converter = getTypeConverter(cls);
            Object objValue = tryConvert(field, -1, converter, value, cls);
            field.set(command, objValue);
            return result;
        }
