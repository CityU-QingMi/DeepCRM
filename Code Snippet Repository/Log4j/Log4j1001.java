        @Override
        public Class<?> convert(final String s) throws ClassNotFoundException {
            switch (s.toLowerCase()) {
                case "boolean":
                    return boolean.class;
                case "byte":
                    return byte.class;
                case "char":
                    return char.class;
                case "double":
                    return double.class;
                case "float":
                    return float.class;
                case "int":
                    return int.class;
                case "long":
                    return long.class;
                case "short":
                    return short.class;
                case "void":
                    return void.class;
                default:
                    return LoaderUtil.loadClass(s);
            }

        }
