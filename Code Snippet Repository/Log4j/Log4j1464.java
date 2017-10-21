        private List<Object> consumeArguments(Field field,
                                              Class<?> annotation,
                                              Range arity,
                                              Stack<String> args,
                                              ITypeConverter<?> converter,
                                              Class<?> type) throws Exception {
            List<Object> result = new ArrayList<Object>();
            int index = 0;

            // first do the arity.min mandatory parameters
            for (int i = 0; result.size() < arity.min; i++) {
                index = consumeOneArgument(field, arity, args, converter, type, result, index);
            }
            // now process the varargs if any
            while (result.size() < arity.max && !args.isEmpty()) {
                if (annotation != Parameters.class) {
                    if (commands.containsKey(args.peek()) || isOption(args.peek())) {
                        return result;
                    }
                }
                index = consumeOneArgument(field, arity, args, converter, type, result, index);
            }
            return result;
        }
