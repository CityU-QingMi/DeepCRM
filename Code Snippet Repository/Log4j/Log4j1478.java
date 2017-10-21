        public static IParamLabelRenderer createMinimalParamLabelRenderer() {
            return new IParamLabelRenderer() {
                public Text renderParameterLabel(Field field, Ansi ansi, List<IStyle> styles) {
                    String paramLabel = null;
                    Parameters parameters = field.getAnnotation(Parameters.class);
                    if (parameters != null) {
                        paramLabel = parameters.paramLabel();
                    } else {
                        paramLabel = field.isAnnotationPresent(Option.class) ? field.getAnnotation(Option.class).paramLabel() : null;
                    }
                    String text = paramLabel == null || paramLabel.length() == 0 ? field.getName() : paramLabel;
                    return ansi.apply(text, styles);
                }
                public String separator() { return ""; }
            };
        }
