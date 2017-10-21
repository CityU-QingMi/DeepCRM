        private int applyOption(Field field,
                                Class<?> annotation,
                                Range arity,
                                boolean valueAttachedToOption,
                                Stack<String> args,
                                Set<Field> initialized) throws Exception {
            updateHelpRequested(field);
            if (!args.isEmpty() && args.peek().length() == 0 && !valueAttachedToOption) {
                args.pop(); // throw out empty string we get at the end of a group of clustered short options
            }
            int length = args.size();
            assertNoMissingParameters(field, arity.min, args);

            Class<?> cls = field.getType();
            if (cls.isArray()) {
                return applyValuesToArrayField(field, annotation, arity, args, cls);
            }
            if (Collection.class.isAssignableFrom(cls)) {
                return applyValuesToCollectionField(field, annotation, arity, args, cls);
            }
            return applyValueToSingleValuedField(field, arity, args, cls, initialized);
        }
