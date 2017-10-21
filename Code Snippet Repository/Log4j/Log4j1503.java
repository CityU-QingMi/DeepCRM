        private void processStandaloneOption(Collection<Field> required,
                                             Set<Field> initialized,
                                             String arg,
                                             Stack<String> args,
                                             boolean paramAttachedToKey) throws Exception {
            Field field = optionName2Field.get(arg);
            required.remove(field);
            Range arity = Range.optionArity(field);
            if (paramAttachedToKey) {
                arity = arity.min(Math.max(1, arity.min)); // if key=value, minimum arity is at least 1
            }
            applyOption(field, Option.class, arity, paramAttachedToKey, args, initialized);
        }
