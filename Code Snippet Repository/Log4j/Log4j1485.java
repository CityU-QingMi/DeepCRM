            public int compare(Field f1, Field f2) {
                Option o1 = f1.getAnnotation(Option.class);
                Option o2 = f2.getAnnotation(Option.class);
                Range arity1 = Range.optionArity(f1);
                Range arity2 = Range.optionArity(f2);
                int result = arity1.max - arity2.max;
                if (result == 0) {
                    result = arity1.min - arity2.min;
                }
                return result == 0 ? super.compare(f1, f2) : result;
            }
