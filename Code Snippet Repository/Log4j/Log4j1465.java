        private int consumeOneArgument(Field field,
                                       Range arity,
                                       Stack<String> args,
                                       ITypeConverter<?> converter,
                                       Class<?> type,
                                       List<Object> result, int index) throws Exception {
            String[] values = split(trim(args.pop()), field);

            // ensure we don't process more than arity.max (as result of splitting args)
            int max = Math.min(arity.max - result.size(), values.length);
            for (int j = 0; j < max; j++) {
                result.add(tryConvert(field, index, converter, values[j], type));
            }
            // if this option cannot consume values because of its arity.max,
            // then push them back on the stack (they are likely processed as positional parameters)
            for (int j = values.length - 1; j >= max; j--) {
                args.push(values[j]);
            }
            index++;
            return index;
        }
