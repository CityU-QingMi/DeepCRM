            public Text renderParameterLabel(Field field, Ansi ansi, List<IStyle> styles) {
                boolean isOptionParameter = field.isAnnotationPresent(Option.class);
                Range arity = isOptionParameter ? Range.optionArity(field) : Range.parameterArity(field);
                Text result = ansi.new Text("");
                String sep = isOptionParameter ? separator : "";
                if (arity.min > 0) {
                    for (int i = 0; i < arity.min; i++) {
                        result = result.append(sep).append(ansi.apply(renderParameterName(field), styles));
                        sep = " ";
                    }
                }
                if (arity.max > arity.min) {
                    sep = result.length == 0 ? (isOptionParameter ? separator : "") : " ";
                    int max = arity.isVariable ? 1 : arity.max - arity.min;
                    for (int i = 0; i < max; i++) {
                        if (sep.trim().length() == 0) {
                            result = result.append(sep + "[").append(ansi.apply(renderParameterName(field), styles));
                        } else {
                            result = result.append("[" + sep).append(ansi.apply(renderParameterName(field), styles));
                        }
                        sep  = " ";
                    }
                    if (arity.isVariable) {
                        result = result.append("...");
                    }
                    for (int i = 0; i < max; i++) { result = result.append("]"); }
                }
                return result;
            }
