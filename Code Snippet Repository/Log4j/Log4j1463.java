        @SuppressWarnings("")
        private int applyValuesToCollectionField(Field field,
                                                 Class<?> annotation,
                                                 Range arity,
                                                 Stack<String> args,
                                                 Class<?> cls) throws Exception {
            Collection<Object> collection = (Collection<Object>) field.get(command);
            Class<?> type = getTypeAttribute(field);
            ITypeConverter<?> converter = getTypeConverter(type);
            List<Object> converted = consumeArguments(field, annotation, arity, args, converter, type);
            if (collection == null) {
                collection = createCollection(cls);
                field.set(command, collection);
            }
            for (Object element : converted) {
                if (element instanceof Collection<?>) {
                    collection.addAll((Collection<?>) element);
                } else {
                    collection.add(element);
                }
            }
            return converted.size();
        }
