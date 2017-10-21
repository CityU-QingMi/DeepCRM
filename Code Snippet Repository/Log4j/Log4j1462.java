        private int applyValuesToArrayField(Field field,
                                            Class<?> annotation,
                                            Range arity,
                                            Stack<String> args,
                                            Class<?> cls) throws Exception {
            Class<?> type = cls.getComponentType();
            ITypeConverter<?> converter = getTypeConverter(type);
            List<Object> converted = consumeArguments(field, annotation, arity, args, converter, cls);
            Object existing = field.get(command);
            int length = existing == null ? 0 : Array.getLength(existing);
            List<Object> newValues = new ArrayList<Object>();
            for (int i = 0; i < length; i++) {
                newValues.add(Array.get(existing, i));
            }
            for (Object obj : converted) {
                if (obj instanceof Collection<?>) {
                    newValues.addAll((Collection<?>) obj);
                } else {
                    newValues.add(obj);
                }
            }
            Object array = Array.newInstance(type, newValues.size());
            field.set(command, array);
            for (int i = 0; i < newValues.size(); i++) {
                Array.set(array, i, newValues.get(i));
            }
            return converted.size(); // return how many args were consumed
        }
